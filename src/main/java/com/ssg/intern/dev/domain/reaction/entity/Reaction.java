package com.ssg.intern.dev.domain.reaction.entity;

import com.ssg.intern.common.BaseEntity;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import lombok.AccessLevel;
import lombok.Builder;
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
public class Reaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean bookmark;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean recommend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @Builder
    public Reaction(final Long accountId, final boolean bookmark, final boolean recommend, final Feed feed) {
        this.accountId = accountId;
        this.bookmark = bookmark;
        this.recommend = recommend;
        this.feed = feed;
    }
}
