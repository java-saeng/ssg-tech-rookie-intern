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
    private Long specialReviewId;

    @Column(nullable = false)
    private Long bookmarkCount;

    @Column(nullable = false)
    private Long recommendCount;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isCommentBlocked;

    private Feed(final Long specialReviewId) {
        this.specialReviewId = specialReviewId;
        this.bookmarkCount = 0L;
        this.recommendCount = 0L;
        this.isCommentBlocked = false;
    }

    public static Feed from(Long specialReviewId) {
        return new Feed(specialReviewId);
    }
}
