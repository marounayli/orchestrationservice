package com.integration.automatic;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailService implements JavaDelegate {

    @Autowired
    private final JavaMailSender javaMailSender;
    private final RuntimeService runtimeService;

    public EmailService(JavaMailSender javaMailSender, RuntimeService runtimeService) {
        this.javaMailSender = javaMailSender;
        this.runtimeService = runtimeService;
    }

    void sendEmail(Map<String,Object> processVariables) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("marounayle@gmail.com");

        msg.setSubject("Order Rejection");
        msg.setText(String.format("Your order has been rejected, information about your order are as follow %s",processVariables));

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
