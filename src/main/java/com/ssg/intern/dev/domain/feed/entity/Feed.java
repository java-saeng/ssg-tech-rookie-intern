package com.ssg.intern.dev.domain.feed.entity;

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
public class Feed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long reviewId;

    @Column(nullable = false)
    private Long bookmarkCount;

    @Column(nullable = false)
    private Long recommendCount;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isCommentBlocked;

    private Feed(final Long reviewId) {
        this.reviewId = reviewId;
    }

    public static Feed from(Long reviewId) {
        return new Feed(reviewId);
    }
}
