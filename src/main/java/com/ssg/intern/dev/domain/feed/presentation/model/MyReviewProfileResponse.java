package com.ssg.intern.dev.domain.feed.presentation.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class MyReviewProfileResponse {

    private long totalReivewCount;
    private List<MyReview> reviews;

    public MyReviewProfileResponse(long totalReivewCount, List<MyReview> reviews) {
        this.totalReivewCount = totalReivewCount;
        this.reviews = reviews;
    }

    @Getter
    @NoArgsConstructor
    public static class MyReview {
        private String imageUrl;
        private float starScore;
        private LocalDateTime createdAt;
        private String description;
        private long recommendCount;
        private long bookmarkCount;
        private long commentCount;
        private boolean isCommentBlocked;

        @Builder
        @QueryProjection
        public MyReview(String imageUrl, float starScore, LocalDateTime createdAt, String description,
                        long recommendCount, long bookmarkCount, long commentCount, boolean isCommentBlocked) {
            this.imageUrl = imageUrl;
            this.starScore = starScore;
            this.createdAt = createdAt;
            this.description = description;
            this.recommendCount = recommendCount;
            this.bookmarkCount = bookmarkCount;
            this.commentCount = commentCount;
            this.isCommentBlocked = isCommentBlocked;
        }


    }
}
