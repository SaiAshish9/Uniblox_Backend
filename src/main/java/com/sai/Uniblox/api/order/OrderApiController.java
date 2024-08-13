package com.sai.Uniblox.api.order;

import com.sai.Uniblox.dto.OrderRequestDTO;
import com.sai.Uniblox.entity.Order;
import com.sai.Uniblox.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/u")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/order/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        orderService.placeOrder(orderRequestDTO);
        return ResponseEntity.ok(orderRequestDTO);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

}