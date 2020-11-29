package com.integration.apirequests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShipmentRequest {
    private String orderId;
    private String unitWeight;
    private String unitDimension;


}
