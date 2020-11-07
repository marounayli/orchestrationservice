package com.integration.apiresponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
public class OrderApprovalResponse {
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
    private Double pricePerUnit;
    private Currency currency;
    private Boolean available;

    @Override
    public String toString() {
        return String.format("Order has been approved\nOrder Description: %s\nQuantity %s\nUnit Dimension %s\n"
                                ,itemDescription,quantity,unitDimensions);
    }
}
