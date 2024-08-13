package com.sai.Uniblox.utils;

import com.sai.Uniblox.entity.Coupon;
import com.sai.Uniblox.enums.CouponApplicableType;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@UtilityClass
public class CouponDataLoader {

    public static List<Coupon> buildCouponData() {
        List<Coupon> result = new ArrayList<>();
        Coupon coupon1 = new Coupon();
        coupon1.setId("DISCOUNT10");
        coupon1.setDiscountPercentage(10.0);
        coupon1.setExpirationDate("2024-12-31T00:00:00.000Z");
        coupon1.setMaxUses(BigInteger.valueOf(100));
        coupon1.setCurrentUses(BigInteger.valueOf(10));
        coupon1.setApplicableTo(List.of(CouponApplicableType.ELECTRONICS, CouponApplicableType.CLOTHING));
        result.add(coupon1);

        Set<String> couponSet = CouponCodeGenerator.generateCoupons(100);
        List<String> coupons = new ArrayList<>(couponSet);

        for (int i = 0; i < 101; i++) {
            Coupon coupon = new Coupon();
            if (i == 0) {
                coupon.setId("FREESHIP");
            } else {
                coupon.setId(coupons.get(i - 1));
            }
            coupon.setDiscountPercentage(20.0);
            coupon.setMaxUses(BigInteger.valueOf(50));
            coupon.setCurrentUses(BigInteger.valueOf(5));
            coupon.setExpirationDate("2024-06-30");
            coupon.setApplicableTo(List.of(CouponApplicableType.ALL));
            result.add(coupon);
        }

        return result;

    }

}
