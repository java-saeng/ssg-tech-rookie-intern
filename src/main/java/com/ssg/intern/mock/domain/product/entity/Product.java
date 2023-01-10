package com.ssg.intern.mock.domain.product.entity;

import com.ssg.intern.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    private Product(final String name, final String description, final int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Product of(final String name, final String description, final int price) {
        return new Product(name, description, price);
    }
}
