package com.integration.apirequests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShipmentRequest {
    private String customerId;
    private String customerEmail;
    private String customerAddress;
    private String unitDimensions;
    private Long unitWeight;
    private String itemId;
    private Integer quantity;
    private String itemDescription;
}
