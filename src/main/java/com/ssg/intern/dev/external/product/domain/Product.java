package com.ssg.intern.dev.external.product.domain;

import com.ssg.intern.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
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
    private int price;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private float starScore;

    @Column(nullable = false)
    private int discountPercent;

    @Builder
    public Product(final String name, final int price, final String imageUrl, final float starScore,
                   final int discountPercent) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.starScore = starScore;
        this.discountPercent = discountPercent;
    }
}
