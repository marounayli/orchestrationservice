package com.integration.apirequests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
public class StockRequest {
    private String itemId;
    private Integer quantity;
    private String itemDescription;
    private Float pricePerUnit;
    private Currency currency;
}
