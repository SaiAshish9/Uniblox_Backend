package com.sai.Uniblox.embeddable;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class CartItem {
    private String id;
    private String img;
    private String title;
    private String desc;
    private String size;
    private String price;
    private String per;
    private String strikePrice;
    private String count;
    private String rating;
    private int qty;
}
