package com.integration.apirequests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockRequest {
    private Integer customerId;
    private Integer productId;
    private Integer quantity;
}
