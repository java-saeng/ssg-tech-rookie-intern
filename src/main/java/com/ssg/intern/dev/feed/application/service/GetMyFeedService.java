package com.ssg.intern.dev.feed.application.service;

import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.external.adapter.in.ExternalMockDataApi;
import com.ssg.intern.dev.feed.application.port.in.GetMyFeedQuery;
import com.ssg.intern.dev.feed.application.port.out.FeedMapper;
import com.ssg.intern.dev.feed.application.port.out.LoadSingleFeedPort;
import com.ssg.intern.dev.feed.domain.Feed;
import com.ssg.intern.dev.feed.domain.MyFeedProfile;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetMyFeedService implements GetMyFeedQuery {

    private final ExternalMockDataApi externalMockDataApi;
    private final FeedMapper feedMapper;
    private final LoadSingleFeedPort loadSingleFeedPort;

    @Override
    public List<MyFeedProfile> getMyFeeds(final long accountId, final SortingCondition sortingCondition) {

        return externalMockDataApi.findSpecialReviewByAccountId(accountId)
                                  .stream()
                                  .map((mockData) -> {

                                      final Feed feed = loadSingleFeedPort
                                              .findBySpecialReviewId(
                                                      mockData.getSpecialReviewProfile().getSpecialReviewId())
                                              .orElseThrow(EntityNotFoundException::new);

                                      return feedMapper.mapToMyFeedProfile(mockData, feed);
                                  })
                                  .sorted((feed1, feed2) -> {

                                      if (sortingCondition == null || sortingCondition.name().equals("NEWER")) {
                                          return feed2.getCreatedAt().compareTo(feed1.getCreatedAt());
                                      }

                                      return feed1.getCreatedAt().compareTo(feed2.getCreatedAt());
                                  })
                                  .collect(Collectors.toList());
    }
}
