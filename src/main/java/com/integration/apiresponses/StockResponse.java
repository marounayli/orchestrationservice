package com.integration.apiresponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
public class StockResponse {
    private String itemId;
    private Integer quantity;
    private String itemDescription;
    private Double pricePerUnit;
    private Currency currency;
    private Boolean available;
}