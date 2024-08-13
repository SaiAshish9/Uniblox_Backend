package com.sai.Uniblox.repository;

import com.sai.Uniblox.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
