package com.ssg.intern.dev.domain.feed.service;

import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileConditionRequest;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse;
import com.ssg.intern.dev.global.SortingCondition;
import com.ssg.intern.mock.MockDataFacadeRepository;
import com.ssg.intern.mock.domain.product.entity.Product;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedQueryService {

    private final FeedRepository feedRepository;
    private final MockDataFacadeRepository mockDataFacadeRepository;
    private final CommentRepository commentRepository;

    public List<FeedProfileResponse> findFeedByCondition(final FeedProfileConditionRequest request) {
        return feedRepository.findAll().stream()
                             .sorted((o1, o2) -> {
                                 if (request.getCondition() == SortingCondition.RECOMMENDATION) {
                                     return o2.getRecommendCount().compareTo(o1.getRecommendCount());
                                 }
                                 return o1.getCreatedAt().compareTo(o2.getCreatedAt());
                             })
                             .map(feed -> {
                                 final SpecialReview specialReview =
                                         mockDataFacadeRepository.findBySpecialReviewId(feed.getId());

                                 return FeedProfileResponse.builder()
                                                           .feedReactionProfile(convertToReaction(feed))
                                                           .commentProfile(convertToComment(feed, specialReview))
                                                           .productProfile(convertToProduct(specialReview))
                                                           .reviewProfile(convertToReview(specialReview))
                                                           .build();
                             })
                             .collect(Collectors.toList());
    }

    private FeedReactionProfile convertToReaction(Feed feed) {
        return new FeedReactionProfile(feed.getBookmarkCount(), feed.getRecommendCount());
    }

    private List<CommentProfile> convertToComment(Feed feed, SpecialReview specialReview) {
        final List<Comment> savedComments = commentRepository.findCommentsByFeedId(feed.getId());

        return savedComments.stream()
                            .map(comment -> {
                                return CommentProfile.builder()
                                                     .author(specialReview.getAccount().getEmail())
                                                     .content(comment.getContent())
                                                     .commentCount(savedComments.size())
                                                     .build();
                            })
                            .collect(Collectors.toList());
    }

    private ProductProfile convertToProduct(SpecialReview specialReview) {
        final Product product = specialReview.getProduct();

        return ProductProfile.builder()
                             .name(product.getName())
                             .imageUrl(product.getImageUrl())
                             .price(product.getPrice())
                             .starScore(product.getStarScore())
                             .discountPercent(product.getDiscountPercent())
                             .build();
    }

    private ReviewProfile convertToReview(SpecialReview specialReview) {

        return ReviewProfile.builder()
                            .createdAt(specialReview.getCreatedAt())
                            .cookLevel(specialReview.getCookLevel().getLevel())
                            .cookQuantity(specialReview.getCookQuantity().getQuantity())
                            .cookTime(specialReview.getCookTime().getTime())
                            .description(specialReview.getDescription())
                            .imageUrl(specialReview.getImageUrl())
                            .starScore(specialReview.getStarScore())
                            .build();
    }
}
