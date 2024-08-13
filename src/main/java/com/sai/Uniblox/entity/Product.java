package com.sai.Uniblox.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

@Entity
@Data
public class Product {
    @Id
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

    @PrePersist
    public void generateUUID() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
