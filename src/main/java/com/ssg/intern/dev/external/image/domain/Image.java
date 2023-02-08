package com.ssg.intern.dev.external.image.domain;

import com.ssg.intern.dev.external.speical_review.domain.SpecialReview;
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
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_review_id")
    private SpecialReview specialReview;

    @Enumerated(value = EnumType.STRING)
    private CookStep cookStep;

    @Builder
    public Image(final String imageUrl, final SpecialReview specialReview, final CookStep cookStep) {
        this.imageUrl = imageUrl;
        this.specialReview = specialReview;
        this.cookStep = cookStep;
    }
}
