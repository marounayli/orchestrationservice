package com.integration.apiresponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShipmentResponse {
    private Integer orderId;
    private Float unitWeight;
    private String unitDimension;
    private String estimatedArrival;
    private String initiatedTime;
    private Boolean initiated;
}
