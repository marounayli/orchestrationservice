package com.integration.automatic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.apiresponses.ShipmentResponse;
import com.integration.apirequests.ShipmentRequest;
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
public class ShipmentAPI implements JavaDelegate {

    private final RuntimeService runtimeService;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ShipmentAPI(RuntimeService runtimeService, ObjectMapper objectMapper,
                    RestTemplate restTemplate, @Value("${api.shipment.url}") String apiUrl) {
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
            ShipmentRequest shipmentRequest = objectMapper.convertValue(processVariables, ShipmentRequest.class);
            HttpEntity<ShipmentRequest> requestEntity = new HttpEntity<>(shipmentRequest);
            ResponseEntity<ShipmentResponse> response = restTemplate.postForEntity(apiUrl, requestEntity, ShipmentResponse.class);
            ShipmentResponse shipmentResponse = response.getBody();
            if (response.getStatusCode() == HttpStatus.OK && Objects.requireNonNull(shipmentResponse).getInitiated()) {
                execution.setVariable("shipment", "initiated");
            } else {
                execution.setVariable("shipment", "not_initiated");
            }
        }
        catch (Exception e){
            execution.setVariable("shipment","not_initiated");
        }
    }
}
