package com.ssg.intern.dev.feed.application.port.out;

import com.ssg.intern.dev.bookmark.application.port.in.GetBookmarkQuery;
import com.ssg.intern.dev.common.ExternalMockDataProfile;
import com.ssg.intern.dev.common.SpecialReviewProfile;
import com.ssg.intern.dev.feed.domain.Feed;
import com.ssg.intern.dev.feed.domain.FeedReactionProfile;
import com.ssg.intern.dev.feed.domain.MyFeedProfile;
import com.ssg.intern.dev.recommend.application.port.in.GetExistenceOfRecommendQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ssg.intern.dev.common.SpecialReviewProfile.*;

@Component
@RequiredArgsConstructor
public class FeedMapper {

    private final GetBookmarkQuery getBookmarkQuery;
    private final GetExistenceOfRecommendQuery getExistenceOfRecommendQuery;

    public FeedReactionProfile mapToFeedReactionProfile(final long accountId, final Feed feed) {
        final long feedId = feed.getId();

        return FeedReactionProfile.builder()
                                  .bookmarkCount(feed.getBookmarkCount())
                                  .recommendCount(feed.getRecommendCount())
                                  .feedId(feedId)
                                  .isBookmarked(getBookmarkQuery.isAccountBookmarkFeed(accountId, feedId))
                                  .isRecommended(getExistenceOfRecommendQuery.isAccountRecommendFeed(accountId, feedId))
                                  .build();
    }

    public MyFeedProfile mapToMyFeedProfile(ExternalMockDataProfile mockData, Feed feed) {

        final SpecialReviewProfile specialReviewProfile = mockData.getSpecialReviewProfile();
        final List<ImageInfo> imageInfos = specialReviewProfile.getImageInfos();

        return MyFeedProfile.builder()
                            .imageUrl(imageInfos.get(imageInfos.size() - 1).getImageUrl())
                            .starScore(specialReviewProfile.getStarScore())
                            .createdAt(specialReviewProfile.getCreatedAt())
                            .description(specialReviewProfile.getDescriptionComplete())
                            .specialReviewId(specialReviewProfile.getSpecialReviewId())
                            .recommendCount(feed.getRecommendCount())
                            .bookmarkCount(feed.getBookmarkCount())
                            .feedId(feed.getId())
                            .build();

    }
}
