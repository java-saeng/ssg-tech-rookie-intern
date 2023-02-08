package com.ssg.intern.dev.feed.application.service;

import com.ssg.intern.dev.common.ExternalMockDataProfile;
import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.external.hashtag.port.out.LoadTop10HashTagPort;
import com.ssg.intern.dev.external.speical_review.port.out.LoadSingleSpecialReviewPort;
import com.ssg.intern.dev.feed.application.port.in.SearchSingleFeedQuery;
import com.ssg.intern.dev.feed.application.port.out.FeedMapper;
import com.ssg.intern.dev.feed.application.port.out.LoadSingleFeedPort;
import com.ssg.intern.dev.feed.domain.FeedProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchSingleFeedService implements SearchSingleFeedQuery {

    private final LoadSingleFeedPort loadSingleFeedPort;
    private final LoadSingleSpecialReviewPort loadSingleSpecialReviewPort;
    private final FeedMapper feedMapper;
    private final LoadTop10HashTagPort loadTop10HashTagPort;

    @Override
    public FeedProfile searchSingleFeed(final long accountId, final long feedId) {
        return loadSingleFeedPort.findById(feedId)
                                 .map(feed -> {

                                     final ExternalMockDataProfile externalMockDataProfile = loadSingleSpecialReviewPort.findBySpecialReviewId(
                                             feed.getSpecialReviewId());

                                     return FeedProfile.builder()
                                                       .hashTagProfile(externalMockDataProfile.getHashTagProfile())
                                                       .productProfile(externalMockDataProfile.getProductProfile())
                                                       .specialReviewProfile(
                                                               externalMockDataProfile.getSpecialReviewProfile())
                                                       .feedReactionProfile(
                                                               feedMapper.mapToFeedReactionProfile(accountId, feed))
                                                       .build();
                                 })
                                 .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public HashTagProfile findTop10HashTag() {
        return loadTop10HashTagPort.loadTop10HashTag();
    }
}
