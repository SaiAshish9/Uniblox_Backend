package com.sai.Uniblox.transformer;

import com.sai.Uniblox.dto.OrderRequestDTO;
import com.sai.Uniblox.entity.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderTransformer {
    public Order buildOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        if (orderRequestDTO != null) {
            order.setId(orderRequestDTO.getId());
            order.setCart(orderRequestDTO.getCart());
            order.setAmount(orderRequestDTO.getAmount());
            order.setTotalDiscountOnMRP(order.getTotalDiscountOnMRP());
            order.setCouponID(orderRequestDTO.getCouponID());
            order.setTotalAmount(orderRequestDTO.getTotalAmount());
            order.setUserId(orderRequestDTO.getUserId());
        }
        return order;
    }
}
