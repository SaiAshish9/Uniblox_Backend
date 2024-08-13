package com.sai.Uniblox.entity;

import com.sai.Uniblox.enums.CouponApplicableType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
public class Coupon {
    @Id
    private String id;
    private Double discountPercentage;
    private BigInteger maxUses;
    private BigInteger currentUses;
    private String expirationDate;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<CouponApplicableType> applicableTo;
}
