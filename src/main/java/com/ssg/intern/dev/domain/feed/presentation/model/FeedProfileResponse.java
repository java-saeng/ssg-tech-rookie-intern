package com.ssg.intern.dev.domain.feed.presentation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class FeedProfileResponse {

    private FeedReactionProfile feedReactionProfile;
    private List<CommentProfile> commentProfile;
    private ProductProfile productProfile;
    private ReviewProfile reviewProfile;

    @Builder
    public FeedProfileResponse(final FeedReactionProfile feedReactionProfile, final List<CommentProfile> commentProfile,
                               final ProductProfile productProfile, final ReviewProfile reviewProfile) {
        this.feedReactionProfile = feedReactionProfile;
        this.commentProfile = commentProfile;
        this.productProfile = productProfile;
        this.reviewProfile = reviewProfile;
    }

    @Getter
    @NoArgsConstructor
    public static class FeedReactionProfile {
        private long bookmarkCount;
        private long recommendCount;

        public FeedReactionProfile(final long bookmarkCount, final long recommendCount) {
            this.bookmarkCount = bookmarkCount;
            this.recommendCount = recommendCount;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class CommentProfile {

        private int commentCount;
        private String content;
        private String author;

        @Builder
        public CommentProfile(final int commentCount, final String content, final String author) {
            this.commentCount = commentCount;
            this.content = content;
            this.author = author;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ProductProfile {
        private String name;
        private String imageUrl;
        private int price;
        private float starScore;
        private int discountPercent;

        @Builder
        public ProductProfile(final String name, final String imageUrl, final int price, final float starScore,
                              final int discountPercent) {
            this.name = name;
            this.imageUrl = imageUrl;
            this.price = price;
            this.starScore = starScore;
            this.discountPercent = discountPercent;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ReviewProfile {
        private LocalDateTime createdAt;
        private String cookLevel;
        private String cookQuantity;
        private String cookTime;
        private String description;
        private String imageUrl;
        private float starScore;

        @Builder
        public ReviewProfile(final LocalDateTime createdAt, final String cookLevel, final String cookQuantity,
                             final String cookTime,
                             final String description, final String imageUrl, final float starScore) {
            this.createdAt = createdAt;
            this.cookLevel = cookLevel;
            this.cookQuantity = cookQuantity;
            this.cookTime = cookTime;
            this.description = description;
            this.imageUrl = imageUrl;
            this.starScore = starScore;
        }
    }
}
