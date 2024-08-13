package com.sai.Uniblox.entity;

import com.sai.Uniblox.embeddable.Address;
import com.sai.Uniblox.embeddable.AppliedCoupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String name;
    private String shortName;
    private String phoneNumber;
    private String profilePicture;

    @ElementCollection
    private List<Address> address;

    @ElementCollection
    private List<AppliedCoupon> appliedCoupons;

    @PrePersist
    public void generateUUID() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
