package com.integration.apiresponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderApprovalResponse {
    private String customerAddress;
    private Integer quantity;
    private String shipment;
    private Integer productId;
    private Integer orderId;
    private Boolean available;
    private Boolean accepted;
    private Float finalPrice;
    private String customerName;
    private String paymentType;
    private Float pricePerUnit;
    private String stock_verification;
    private String customerEmail;
    private Integer customerId;
    private String currency;
    private String payment;
    private String cardNumber;
    private String productDescription;


    @Override
    public String toString() {
        return String.format("Order has been approved\nOrder Description: %s\nQuantity %s\nTotal price %s%s\n"
                                ,productDescription,quantity,finalPrice,currency);
    }
}
