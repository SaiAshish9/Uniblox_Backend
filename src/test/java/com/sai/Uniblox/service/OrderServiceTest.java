package com.sai.Uniblox.service;

import com.sai.Uniblox.dto.OrderRequestDTO;
import com.sai.Uniblox.embeddable.AppliedCoupon;
import com.sai.Uniblox.entity.Order;
import com.sai.Uniblox.entity.User;
import com.sai.Uniblox.repository.OrderRepository;
import com.sai.Uniblox.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private static final String ORDER_ID = "k4sf6Ld7x-QpWJ_QwVA79";
    private static final String USER_ID = "b75d9787-ab35-45a3-8d1c-e865d9d244ea";
    private static final String COUPON_ID = "DISCOUNT10";

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderRequestDTO orderRequestDTO;
    private User user;
    private Order order;

    @BeforeEach
    void setUp() {
        // Initialize mock data
        orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setId(ORDER_ID);
        orderRequestDTO.setUserId(USER_ID);
        orderRequestDTO.setCouponID(COUPON_ID);

        order = new Order();
        order.setId(ORDER_ID);
        order.setCouponID(COUPON_ID);
        order.setUserId(USER_ID);

        user = new User();
        user.setId(USER_ID);
        user.setAppliedCoupons(new ArrayList<>());
    }

    @Test
    void testPlaceOrder_withValidUserAndCoupon() {
        // Mock the behavior of the transformer and repository
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        // Call the method under test
        orderService.placeOrder(orderRequestDTO);

        // Verify that the order was saved
        verify(orderRepository, times(1)).save(order);

        // Assert that the coupon was added to the user's list of applied coupons
        assertEquals(1, user.getAppliedCoupons().size());
        AppliedCoupon appliedCoupon = user.getAppliedCoupons().get(0);
        assertEquals(COUPON_ID, appliedCoupon.getId());
        assertEquals(ORDER_ID, appliedCoupon.getOrderId()); // Correct comparison
    }

    @Test
    void testPlaceOrder_withInvalidUser() {
        // Mock the behavior of the repository to return an empty optional
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        // Call the method under test
        orderService.placeOrder(orderRequestDTO);

        // Verify that the order was saved
        verify(orderRepository, times(1)).save(any(Order.class));

        // Verify that the user was not updated
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetAllOrders() {
        // Mock the behavior of the repository to return a list of orders
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderRepository.findAll()).thenReturn(orders);

        // Call the method under test
        List<Order> result = orderService.getAllOrders();

        // Verify the result
        assertEquals(1, result.size());
        assertTrue(result.contains(order));

        // Verify that the repository method was called once
        verify(orderRepository, times(1)).findAll();
    }
}
