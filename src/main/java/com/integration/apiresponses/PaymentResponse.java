package com.integration.apiresponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    private String customerId;
    private String customerEmail;
    private Integer orderId;
    private String paymentType;
    private String cardNumber;
    private Integer productId;
    private Integer quantity;
    private Float pricePerUnit;
    private Boolean accepted;
    private String productDescription;
    private Float finalPrice;
    private String currency;


}
