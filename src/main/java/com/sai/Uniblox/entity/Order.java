package com.sai.Uniblox.entity;

import com.sai.Uniblox.embeddable.CartItem;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    private String id;

    @ElementCollection
    @CollectionTable(name = "order_cart", joinColumns = @JoinColumn(name = "order_id"))
    private List<CartItem> cart;
    private double amount;
    private double totalDiscountOnMRP;

    private String couponID;
    private String totalAmount;
    private String userId;

    @PrePersist
    public void generateUUID() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}