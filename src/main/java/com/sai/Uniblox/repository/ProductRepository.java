package com.sai.Uniblox.repository;

import com.sai.Uniblox.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
