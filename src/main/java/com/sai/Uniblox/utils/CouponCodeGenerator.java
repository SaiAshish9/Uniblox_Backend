package com.sai.Uniblox.utils;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class CouponCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateCouponCode() {
        int length = 10;
        StringBuilder couponCode = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            couponCode.append(CHARACTERS.charAt(randomIndex));
        }
        return couponCode.toString();
    }

    public static Set<String> generateCoupons(int numberOfCoupons) {
        Set<String> coupons = new HashSet<>();
        while (coupons.size() < numberOfCoupons) {
            coupons.add(generateCouponCode());
        }
        return coupons;
    }

}
