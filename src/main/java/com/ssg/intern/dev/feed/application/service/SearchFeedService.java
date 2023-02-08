package com.ssg.intern.dev.feed.application.service;


import com.ssg.intern.dev.common.FeedSearchingConditionRequest;
import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.external.adapter.in.ExternalMockDataApi;
import com.ssg.intern.dev.feed.application.port.in.SearchFeedQuery;
import com.ssg.intern.dev.feed.application.port.out.FeedMapper;
import com.ssg.intern.dev.feed.application.port.out.LoadSingleFeedPort;
import com.ssg.intern.dev.feed.domain.Feed;
import com.ssg.intern.dev.feed.domain.FeedProfile;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchFeedService implements SearchFeedQuery {

    // SpecialReview를 받아오는 외부 API Client 라 가정
    private final ExternalMockDataApi externalMockDataApi;
    private final LoadSingleFeedPort loadSingleFeedPort;
    private final FeedMapper feedMapper;


    @Override
    public List<FeedProfile> searchFeeds(final Pageable pageable, final FeedSearchingConditionRequest request,
                                         final long accountId) {

        return externalMockDataApi.findBySearchingCondition(pageable, request)
                                  .stream()
                                  .map((specialReview) -> {

                                      final Feed feed = loadSingleFeedPort.findBySpecialReviewId(
                                                                                  specialReview
                                                                                          .getSpecialReviewProfile()
                                                                                          .getSpecialReviewId())
                                                                          .orElseThrow(
                                                                                  EntityNotFoundException::new);

                                      return FeedProfile.builder()
                                                        .hashTagProfile(specialReview.getHashTagProfile())
                                                        .productProfile(specialReview.getProductProfile())
                                                        .specialReviewProfile(
                                                                specialReview.getSpecialReviewProfile())
                                                        .feedReactionProfile(
                                                                feedMapper.mapToFeedReactionProfile(
                                                                        accountId,
                                                                        feed))
                                                        .build();
                                  })
                                  .sorted((feed1, feed2) -> {
                                      final Sort sort = pageable.getSort();

                                      for (final Sort.Order order : sort) {
                                          if (order.getProperty().equals(SortingCondition.RECOMMENDATION.name())) {
                                              return (int) (feed2.getFeedReactionProfile().getRecommendCount()
                                                      - feed1.getFeedReactionProfile().getRecommendCount());
                                          }
                                      }

                                      return feed1.getSpecialReviewProfile().getCreatedAt().compareTo(
                                              feed2.getSpecialReviewProfile().getCreatedAt()
                                      );
                                  })
                                  .collect(Collectors.toList());
    }

    @Override
    public HashTagProfile findTop10HashTag() {
        return externalMockDataApi.loadTop10HashTag();
    }
}
