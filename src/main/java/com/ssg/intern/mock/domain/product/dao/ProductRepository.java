package com.ssg.intern.mock.domain.product.dao;

import com.ssg.intern.mock.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
