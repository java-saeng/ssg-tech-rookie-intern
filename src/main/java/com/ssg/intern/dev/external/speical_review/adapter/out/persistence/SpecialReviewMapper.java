package com.ssg.intern.dev.external.speical_review.adapter.out.persistence;

import com.ssg.intern.dev.common.SpecialReviewProfile;
import com.ssg.intern.dev.external.hashtag.adapter.out.HashTagMapper;
import com.ssg.intern.dev.external.product.adapter.out.ProductMapper;
import com.ssg.intern.dev.external.speical_review.domain.SpecialReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SpecialReviewMapper {

    private final ProductMapper productMapper;
    private final HashTagMapper hashTagMapper;

    public SpecialReviewProfile mapToSpecialReviewProfile(SpecialReview specialReview) {

        final List<SpecialReviewProfile.ImageInfo> imageInfos =
                specialReview.getImages()
                             .stream()
                             .map((image) -> {
                                 return SpecialReviewProfile.ImageInfo.builder()
                                                                      .imageUrl(image.getImageUrl())
                                                                      .imageId(image.getId())
                                                                      .cookStep(image.getCookStep().name())
                                                                      .build();
                             })
                             .collect(Collectors.toList());

        return SpecialReviewProfile.builder()
                                   .createdAt(specialReview.getCreatedAt())
                                   .cookLevel(specialReview.getCookLevel().getLevel())
                                   .cookQuantity(specialReview.getCookQuantity().getQuantity())
                                   .cookTime(specialReview.getCookTime().getTime())
                                   .descriptionIngredient(specialReview.getDescriptionIngredient())
                                   .descriptionProcess(specialReview.getDescriptionProcess())
                                   .descriptionComplete(specialReview.getDescriptionComplete())
                                   .imageInfos(imageInfos)
                                   .starScore(specialReview.getStarScore())
                                   .author(specialReview.getAccount().getEmail())
                                   .specialReviewId(specialReview.getId())
                                   .build();
    }
}
