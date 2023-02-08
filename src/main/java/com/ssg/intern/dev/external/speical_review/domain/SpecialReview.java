package com.ssg.intern.dev.external.speical_review.domain;

import com.ssg.intern.common.BaseEntity;
import com.ssg.intern.common.domain.account.entity.Account;
import com.ssg.intern.dev.external.hashtag.domain.HashTag;
import com.ssg.intern.dev.external.image.domain.Image;
import com.ssg.intern.dev.external.product.domain.Product;
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
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specialReview")
    private final List<HashTag> hashTags = new ArrayList<>();

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
    @OneToMany(mappedBy = "specialReview", fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @Column(nullable = false)
    private String descriptionIngredient;

    @Column(nullable = false)
    private String descriptionProcess;

    @Column(nullable = false)
    private String descriptionComplete;

    @Column(nullable = false)
    private float starScore;

    @Builder
    public SpecialReview(final Product product, final Account account, final CookLevel cookLevel,
                         final CookQuantity cookQuantity,
                         final CookTime cookTime, final List<Image> images, final String descriptionIngredient,
                         final String descriptionProcess,
                         final String descriptionComplete, final float starScore) {
        this.product = product;
        this.account = account;
        this.cookLevel = cookLevel;
        this.cookQuantity = cookQuantity;
        this.cookTime = cookTime;
        this.images = images;
        this.descriptionIngredient = descriptionIngredient;
        this.descriptionProcess = descriptionProcess;
        this.descriptionComplete = descriptionComplete;
        this.starScore = starScore;
    }
}
