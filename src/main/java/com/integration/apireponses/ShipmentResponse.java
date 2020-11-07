package com.integration.apireponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShipmentResponse {
    private String customerId;
    private String customerEmail;
    private String customerAddress;
    private String unitDimensions;
    private Integer unitWeight;
    private String itemId;
    private Integer quantity;
    private String itemDescription;
    private Boolean initiated;
    private String estimatedTimeOfArrival;
}
