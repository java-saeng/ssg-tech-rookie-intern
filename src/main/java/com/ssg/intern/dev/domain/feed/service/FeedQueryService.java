package com.ssg.intern.dev.domain.feed.service;

import com.ssg.intern.dev.domain.bookmark.service.BookmarkQueryService;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedSearchingConditionRequest;
import com.ssg.intern.dev.domain.recommend.service.RecommendQueryService;
import com.ssg.intern.mock.MockDataFacadeRepository;
import com.ssg.intern.mock.domain.hashtag.entity.HashTag;
import com.ssg.intern.mock.domain.product.entity.Product;
import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedQueryService {

    private final FeedRepository feedRepository;
    private final MockDataFacadeRepository mockDataFacadeRepository;
    private final BookmarkQueryService bookmarkQueryService;
    private final RecommendQueryService recommendQueryService;

    public FeedProfileResponse showOneFeed(final long feedId) {
        return feedRepository.findById(feedId)
                             .map(feed -> {
                                 final SpecialReview specialReview =
                                         mockDataFacadeRepository.findBySpecialReviewId(feed.getId());

                                 return FeedProfileResponse.builder()
                                                           .feedReactionProfile(convertToReaction(feed, specialReview))
                                                           .productProfile(convertToProduct(specialReview))
                                                           .reviewProfile(convertToReview(specialReview))
                                                           .build();
                             })
                             .orElseThrow(EntityNotFoundException::new);
    }

    public List<FeedProfileResponse> showSatisfiedConditionFeeds(final Pageable pageable,
                                                                 final FeedSearchingConditionRequest request) {

        return mockDataFacadeRepository.findBySearchingCondition(pageable, request)
                                       .stream()
                                       .map(specialReview -> {

                                           final Feed feed = feedRepository.findById(specialReview.getId())
                                                                           .orElseThrow(EntityNotFoundException::new);

                                           return FeedProfileResponse.builder()
                                                                     .feedReactionProfile(convertToReaction(feed, specialReview))
                                                                     .productProfile(convertToProduct(specialReview))
                                                                     .reviewProfile(convertToReview(specialReview))
                                                                     .hashTags(specialReview.getHashTags()
                                                                                            .stream()
                                                                                            .map((HashTag::getName))
                                                                                            .collect(Collectors.toList()))
                                                                     .build();
                                       })
                                       .collect(Collectors.toList());
    }

    private FeedReactionProfile convertToReaction(Feed feed, SpecialReview specialReview) {

        final Long accountId = specialReview.getAccount().getId();
        final Long feedId = feed.getId();

        return FeedReactionProfile.builder()
                                  .feedId(feedId)
                                  .bookmarkCount(feed.getBookmarkCount())
                                  .recommendCount(feed.getRecommendCount())
                                  //TODO : DTO 분리 시켜서 Http header에서 accountId를 받아서 1L 대신 넣어주기
                                  .isRecommended(recommendQueryService.isAccountRecommendFeed(1L, feedId))
                                  .isBookmarked(bookmarkQueryService.isAccountBookmarkFeed(1L, feedId))
                                  .build();
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
                            .author(specialReview.getAccount().getEmail())
                            .build();
    }
}
