package com.ssg.intern.mock.domain.review.entity;

import com.ssg.intern.common.BaseEntity;
import com.ssg.intern.common.domain.account.entity.Account;
import com.ssg.intern.mock.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SpecialReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_review_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CookLevel cookLevel;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CookQuantity cookQuantity;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CookTime cookTime;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String description;

    @Builder
    public SpecialReview(final Product product, final Account account, final CookLevel cookLevel,
                         final CookQuantity cookQuantity,
                         final CookTime cookTime, final String imageUrl, final String description) {
        this.product = product;
        this.account = account;
        this.cookLevel = cookLevel;
        this.cookQuantity = cookQuantity;
        this.cookTime = cookTime;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
