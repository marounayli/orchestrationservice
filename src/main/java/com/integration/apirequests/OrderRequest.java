package com.integration.apirequests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {
    private String customerId;
    private String customerEmail;
    private String customerAddress;
    private String unitDimensions;
    private Integer unitWeight;
    private String itemId;
    private Integer quantity;
    private String itemDescription;
    private Float pricePerUnit;
    private Currency currency;
}
