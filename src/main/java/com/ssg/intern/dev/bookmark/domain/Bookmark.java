package com.ssg.intern.dev.bookmark.domain;

import com.ssg.intern.common.BaseEntity;
import com.ssg.intern.dev.feed.domain.Feed;
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
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isBookmarked;

    private Bookmark(final Long accountId, final Feed feed, final boolean isBookmarked) {
        this.accountId = accountId;
        this.feed = feed;
        this.isBookmarked = isBookmarked;
    }

    public static Bookmark of(final Long accountId, final Feed feed, final boolean isBookmarked) {
        return new Bookmark(accountId, feed, isBookmarked);
    }

    public void addBookmark() {
        isBookmarked = true;
    }

    public void cancelBookmark() {
        isBookmarked = false;
    }
}
