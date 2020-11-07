package com.integration.apirequests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {

    private String customerId;
    private String customerEmail;
    private String customerAddress;
    private Integer quantity;
    private Float pricePerUnit;
    private Currency currency;

}
