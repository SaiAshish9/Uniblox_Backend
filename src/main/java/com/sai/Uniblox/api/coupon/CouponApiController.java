package com.sai.Uniblox.api.coupon;

import com.sai.Uniblox.entity.Coupon;
import com.sai.Uniblox.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/u")
@RequiredArgsConstructor
public class CouponApiController {

    private final CouponService couponService;

    @GetMapping("/coupons")
    public List<Coupon> getCoupons() {
        return couponService.getAllCoupons();
    }

}
