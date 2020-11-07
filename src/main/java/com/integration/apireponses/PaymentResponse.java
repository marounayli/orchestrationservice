package com.integration.apireponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    private String customerId;
    private String customerEmail;
    private String customerAddress;
    private Integer quantity;
    private Double pricePerUnit;
    private Currency currency;
    private String paymentType;
    private Double totalPrice;
    private Double taxRate;
    private Boolean approved;


}
