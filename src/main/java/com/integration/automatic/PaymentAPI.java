package com.integration.automatic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.apiresponses.PaymentResponse;
import com.integration.apirequests.PaymentRequest;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Component
@PropertySource(value={"classpath:apis.properties"})
public class PaymentAPI implements JavaDelegate {

    private final RuntimeService runtimeService;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public PaymentAPI(RuntimeService runtimeService, ObjectMapper objectMapper,
                    RestTemplate restTemplate, @Value("${api.payment.url}") String apiUrl) {
        this.runtimeService = runtimeService;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try {
            String processInstanceId = execution.getProcessInstanceId();
            Map<String, Object> processVariables = runtimeService.getVariables(processInstanceId);
            PaymentRequest paymentRequest = objectMapper.convertValue(processVariables, PaymentRequest.class);
            HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(paymentRequest);
            ResponseEntity<PaymentResponse> response = restTemplate.postForEntity(apiUrl, requestEntity, PaymentResponse.class);
            PaymentResponse paymentResponse =response.getBody();
            if (response.getStatusCode() == HttpStatus.OK && Objects.requireNonNull(paymentResponse).getAccepted()) {
                execution.setVariable("payment", "approved");
                execution.setVariables(objectMapper.convertValue(paymentResponse, new TypeReference<Map<String, ?>>() {}));
            } else {
                execution.setVariable("payment", "not_approved");
            }
        }
        catch (Exception e){
            execution.setVariable("payment","not_approved");
        }
    }
}
