package com.integration.automatic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.apireponses.StockResponse;
import com.integration.apirequests.StockRequest;
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
public class StockAPI implements JavaDelegate {

    private final RuntimeService runtimeService;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public StockAPI(RuntimeService runtimeService, ObjectMapper objectMapper,
                    RestTemplate restTemplate, @Value("${api.stock.url}") String apiUrl) {
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
            StockRequest stockRequest = objectMapper.convertValue(processVariables, StockRequest.class);
            HttpEntity<StockRequest> requestEntity = new HttpEntity<>(stockRequest);
            ResponseEntity<StockResponse> response = restTemplate.postForEntity(apiUrl, requestEntity, StockResponse.class);
            StockResponse stockResponse = response.getBody();
            if (response.getStatusCode() == HttpStatus.OK && Objects.requireNonNull(stockResponse).getAvailable()) {
                execution.setVariable("stock_verification", "verified");
                execution.setVariables(objectMapper.convertValue(stockResponse, new TypeReference<Map<String, ?>>() {}));
            } else {
                execution.setVariable("stock_verification", "not_verified");
            }
        }
        catch (Exception e){
            execution.setVariable("stock_verification","not_verified");
        }
    }
}
