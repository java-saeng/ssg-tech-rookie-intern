package com.ssg.intern.dev.feed.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedReactionProfile {
    private long bookmarkCount;
    private long recommendCount;
    private long feedId;

    //TODO : bookmark, recommend 표시 DTO 분리
    private boolean isBookmarked;
    private boolean isRecommended;

    @Builder
    public FeedReactionProfile(final long bookmarkCount, final long recommendCount, final long feedId,
                               final boolean isBookmarked, final boolean isRecommended) {
        this.bookmarkCount = bookmarkCount;
        this.recommendCount = recommendCount;
        this.feedId = feedId;
        this.isBookmarked = isBookmarked;
        this.isRecommended = isRecommended;
    }
}
