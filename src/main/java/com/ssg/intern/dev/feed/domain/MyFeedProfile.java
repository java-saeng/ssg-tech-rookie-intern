package com.ssg.intern.dev.feed.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyFeedProfile {

    private String imageUrl;
    private float starScore;
    private LocalDateTime createdAt;
    private String description;
    private long recommendCount;
    private long bookmarkCount;
    private long specialReviewId;
    private long feedId;

    @Builder
    public MyFeedProfile(final String imageUrl, final float starScore, final LocalDateTime createdAt,
                         final String description, final long recommendCount,
                         final long bookmarkCount, final long specialReviewId, final long feedId) {
        this.imageUrl = imageUrl;
        this.starScore = starScore;
        this.createdAt = createdAt;
        this.description = description;
        this.recommendCount = recommendCount;
        this.bookmarkCount = bookmarkCount;
        this.specialReviewId = specialReviewId;
        this.feedId = feedId;
    }
}
