package com.sai.Uniblox.api.coupon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/u")
public class CouponApiController {

    @GetMapping("/coupons")
    public List<String> getCoupons(){
        return Arrays.asList("ABC", "XYZ");
    }

}
