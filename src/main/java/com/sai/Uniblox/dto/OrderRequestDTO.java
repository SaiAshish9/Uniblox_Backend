package com.sai.Uniblox.dto;

import com.sai.Uniblox.embeddable.CartItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private String id;
    private List<CartItem> cart;
    private double amount;
    private double totalDiscountOnMRP;
    private String couponID;
    private String totalAmount;
    private String userId;
}
