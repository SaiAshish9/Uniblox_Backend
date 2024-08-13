package com.sai.Uniblox.embeddable;

import lombok.Data;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class AppliedCoupon {
    private String id;
    private String orderId;

}

