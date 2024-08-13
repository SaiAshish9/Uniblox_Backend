package com.sai.Uniblox.api.order;

import com.sai.Uniblox.dto.OrderRequestDTO;
import com.sai.Uniblox.entity.Order;
import com.sai.Uniblox.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/u")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/order/place")
    public ResponseEntity getProducts(OrderRequestDTO orderRequestDTO) {
        orderService.placeOrder(orderRequestDTO);
        return ResponseEntity.ok(orderRequestDTO);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

}