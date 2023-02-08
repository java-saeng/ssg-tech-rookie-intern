package com.ssg.intern.dev.external.hashtag.domain;

import com.ssg.intern.common.BaseEntity;
import com.ssg.intern.dev.external.speical_review.domain.SpecialReview;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HashTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_review_id", nullable = false)
    private SpecialReview specialReview;

    @Column(nullable = false)
    private String name;

    private HashTag(final SpecialReview specialReview, final String name) {
        this.specialReview = specialReview;
        this.name = name;
    }

    public static HashTag of(final SpecialReview specialReview, final String name) {
        return new HashTag(specialReview, name);
    }
}
