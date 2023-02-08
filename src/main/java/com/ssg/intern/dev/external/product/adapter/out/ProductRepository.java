package com.ssg.intern.dev.external.product.adapter.out;

import com.ssg.intern.dev.external.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
