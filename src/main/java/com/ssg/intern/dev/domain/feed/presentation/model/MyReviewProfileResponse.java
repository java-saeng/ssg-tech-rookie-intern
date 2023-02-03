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

    private long totalReviewCount;
    private List<MyReview> reviews;

    public MyReviewProfileResponse(long totalReviewCount, List<MyReview> reviews) {
        this.totalReviewCount = totalReviewCount;
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
        private long specialReviewId;

        @Builder
        @QueryProjection
        public MyReview(String imageUrl, float starScore, LocalDateTime createdAt, String description,
                        long recommendCount, long bookmarkCount, long specialReviewId) {
            this.imageUrl = imageUrl;
            this.starScore = starScore;
            this.createdAt = createdAt;
            this.description = description;
            this.recommendCount = recommendCount;
            this.bookmarkCount = bookmarkCount;
            this.specialReviewId = specialReviewId;
        }


    }
}
