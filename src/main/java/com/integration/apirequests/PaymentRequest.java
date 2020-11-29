package com.integration.apirequests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {

    private String customerId;
    private String customerEmail;
    private Integer orderId;
    private String cardNumber;
    private String paymentType;

}
