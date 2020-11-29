package com.integration.apiresponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockResponse {
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private Integer productId;
    private String productDescription;
    private Integer quantity;
    private Float pricePerUnit;
    private String currency;
    private Boolean available;
    private String orderId;
}