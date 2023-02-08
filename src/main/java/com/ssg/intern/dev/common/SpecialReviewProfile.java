package com.ssg.intern.dev.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class SpecialReviewProfile {
    private LocalDateTime createdAt;
    private String cookLevel;
    private String cookQuantity;
    private String cookTime;
    private String descriptionIngredient;
    private String descriptionProcess;
    private String descriptionComplete;
    private List<ImageInfo> imageInfos;
    private float starScore;
    private String author;
    private long specialReviewId;

    @Builder
    public SpecialReviewProfile(final LocalDateTime createdAt, final String cookLevel, final String cookQuantity,
                                final String cookTime, final String descriptionIngredient, final String descriptionComplete,
                                final String descriptionProcess, final List<ImageInfo> imageInfos, final float starScore,
                                final String author, final long specialReviewId) {

        final String sb = author.substring(0, 3) +
                "*******";

        this.createdAt = createdAt;
        this.cookLevel = cookLevel;
        this.cookQuantity = cookQuantity;
        this.cookTime = cookTime;
        this.descriptionIngredient = descriptionIngredient;
        this.descriptionProcess = descriptionProcess;
        this.descriptionComplete = descriptionComplete;
        this.imageInfos = imageInfos;
        this.starScore = starScore;
        this.author = sb;
        this.specialReviewId = specialReviewId;
    }

    @Getter
    @NoArgsConstructor
    public static class ImageInfo {

        private long imageId;
        private String imageUrl;
        private String cookStep;

        @Builder
        public ImageInfo(final long imageId, final String imageUrl, final String cookStep) {
            this.imageId = imageId;
            this.imageUrl = imageUrl;
            this.cookStep = cookStep;
        }
    }
}
