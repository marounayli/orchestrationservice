package com.integration.automatic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.apiresponses.OrderApprovalResponse;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShipmentEmailNotification implements JavaDelegate {

    @Autowired
    private final JavaMailSender javaMailSender;
    private final RuntimeService runtimeService;
    private final ObjectMapper objectMapper;

    public ShipmentEmailNotification(JavaMailSender javaMailSender, RuntimeService runtimeService, ObjectMapper objectMapper) {
        this.javaMailSender = javaMailSender;
        this.runtimeService = runtimeService;
        this.objectMapper = objectMapper;
    }

    void sendEmail(Map<String,Object> processVariables) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("marounayle@gmail.com");

        msg.setSubject("Order Approved");
        OrderApprovalResponse orderApprovalResponse = objectMapper.convertValue(processVariables,OrderApprovalResponse.class);

        msg.setText(orderApprovalResponse.toString());

        javaMailSender.send(msg);

    }

    @Override
    public void execute(DelegateExecution execution){
        String processInstanceId = execution.getProcessInstanceId();
        Map<String, Object> processVariables = runtimeService.getVariables(processInstanceId);
        sendEmail(processVariables);
        System.out.println("email sent");
    }
}
