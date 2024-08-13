package com.sai.Uniblox.service;

import com.sai.Uniblox.dto.OrderRequestDTO;
import com.sai.Uniblox.embeddable.AppliedCoupon;
import com.sai.Uniblox.entity.Order;
import com.sai.Uniblox.entity.User;
import com.sai.Uniblox.repository.OrderRepository;
import com.sai.Uniblox.repository.UserRepository;
import com.sai.Uniblox.transformer.OrderTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public void placeOrder(OrderRequestDTO orderRequestDTO) {
        Order order = OrderTransformer.buildOrder(orderRequestDTO);
        orderRepository.save(order);
        User user = userRepository.findById(orderRequestDTO.getUserId()).orElse(null);
        if (user != null && orderRequestDTO.getCouponID() != null) {
           List<AppliedCoupon> coupons = user.getAppliedCoupons();
           AppliedCoupon appliedCoupon = new AppliedCoupon();
           appliedCoupon.setId(orderRequestDTO.getCouponID());
           appliedCoupon.setOrderId(orderRequestDTO.getId());
           coupons.add(appliedCoupon);
           user.setAppliedCoupons(coupons);
           userRepository.save(user);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
