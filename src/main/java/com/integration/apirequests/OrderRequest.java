package com.integration.apirequests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {
    private Integer customerId;
    private Integer productId;
    private Integer quantity;
    private String customerEmail;
    private String cardNumber;
    private String paymentType;
}
