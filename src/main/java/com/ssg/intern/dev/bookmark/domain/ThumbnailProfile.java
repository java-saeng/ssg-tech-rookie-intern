package com.ssg.intern.dev.bookmark.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ThumbnailProfile {

    private String imageUrl;
    private long recommendCount;
    private String description;
    private boolean isRecommended;
    private long feedId;

    @Builder
    public ThumbnailProfile(final String imageUrl, final long recommendCount, final String description,
                            final boolean isRecommended, final long feedId) {
        this.imageUrl = imageUrl;
        this.recommendCount = recommendCount;
        this.description = description;
        this.isRecommended = isRecommended;
        this.feedId = feedId;
    }
}
