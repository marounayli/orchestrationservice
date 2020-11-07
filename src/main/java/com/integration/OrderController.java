package com.integration;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.apirequests.OrderRequest;
import com.integration.apirequests.StockRequest;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final RuntimeService runtimeService;
    private final ObjectMapper objectMapper;
    public OrderController(RuntimeService runtimeService, ObjectMapper objectMapper) {
        this.runtimeService = runtimeService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/start/{processDefinitionKey}")
    public String startProcess(@RequestBody OrderRequest orderRequest, @PathVariable String processDefinitionKey){
        try{
            Map<String,Object> variables = objectMapper.convertValue(orderRequest,new TypeReference<Map<String, Object>>() {});
            runtimeService.startProcessInstanceByKey(processDefinitionKey,variables);
            return String.format("Process Started variables = %s",variables.toString());
//            return Currency.getInstance(Locale.US);
        } catch (Exception e){
//          return Currency.getInstance(Locale.GERMAN);
          return "Failed to start process Instance";
        }
    }
}
