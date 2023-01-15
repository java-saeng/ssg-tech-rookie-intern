package com.ssg.intern.dev.domain.recommend.entity;

import com.ssg.intern.common.BaseEntity;
import com.ssg.intern.dev.domain.feed.entity.Feed;
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
public class Recommend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isRecommended;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    private Recommend(final Long accountId, final boolean isRecommended, final Feed feed) {
        this.accountId = accountId;
        this.isRecommended = isRecommended;
        this.feed = feed;
    }

    public static Recommend of(final Long accountId, final boolean isRecommended, final Feed feed) {
        return new Recommend(accountId, isRecommended, feed);
    }

    public void addRecommend() {
        isRecommended = true;
    }
}
