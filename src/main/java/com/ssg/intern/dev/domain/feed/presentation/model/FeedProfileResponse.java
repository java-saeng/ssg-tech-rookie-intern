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

    private List<String> hashTags;

    @Builder
    public FeedProfileResponse(final FeedReactionProfile feedReactionProfile, final List<CommentProfile> commentProfile,
                               final ProductProfile productProfile, final ReviewProfile reviewProfile,
                               List<String> hashTags) {
        this.feedReactionProfile = feedReactionProfile;
        this.commentProfile = commentProfile;
        this.productProfile = productProfile;
        this.reviewProfile = reviewProfile;
        this.hashTags = hashTags;
    }

    @Getter
    @NoArgsConstructor
    public static class FeedReactionProfile {
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


    @Getter
    @NoArgsConstructor
    public static class CommentProfile {

        private int commentCount;
        private String content;
        private String author;
        private boolean isCommentBlocked;

        @Builder
        public CommentProfile(final int commentCount, final String content, final String author,
                              final boolean isCommentBlocked) {
            this.commentCount = commentCount;
            this.content = content;
            this.author = author;
            this.isCommentBlocked = isCommentBlocked;
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
        private String author;

        @Builder
        public ReviewProfile(final LocalDateTime createdAt, final String cookLevel, final String cookQuantity,
                             final String cookTime,
                             final String description, final String imageUrl, final float starScore,
                             final String author) {
            StringBuilder sb = new StringBuilder();
            sb.append(author.substring(0, 3));
            sb.append("*******");

            this.createdAt = createdAt;
            this.cookLevel = cookLevel;
            this.cookQuantity = cookQuantity;
            this.cookTime = cookTime;
            this.description = description;
            this.imageUrl = imageUrl;
            this.starScore = starScore;
            this.author = sb.toString();
        }
    }
}
