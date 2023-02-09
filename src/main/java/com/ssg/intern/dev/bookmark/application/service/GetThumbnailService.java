package com.ssg.intern.dev.bookmark.application.service;

import com.ssg.intern.dev.bookmark.application.port.in.GetThumbnailQuery;
import com.ssg.intern.dev.bookmark.application.port.out.LoadMyBookmarkPort;
import com.ssg.intern.dev.bookmark.domain.ThumbnailProfile;
import com.ssg.intern.dev.common.ExternalMockDataProfile;
import com.ssg.intern.dev.common.SpecialReviewProfile;
import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.external.adapter.in.ExternalMockDataApi;
import com.ssg.intern.dev.feed.domain.Feed;
import com.ssg.intern.dev.global.SortingCondition;
import com.ssg.intern.dev.recommend.application.port.in.GetExistenceOfRecommendQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssg.intern.dev.common.SpecialReviewProfile.ImageInfo;

@UseCase
@RequiredArgsConstructor
public class GetThumbnailService implements GetThumbnailQuery {

    private final ExternalMockDataApi externalMockDataApi;
    private final LoadMyBookmarkPort myBookmarkPort;
    private final GetExistenceOfRecommendQuery getExistenceOfRecommendQuery;

    @Override
    public List<ThumbnailProfile> getThumbnails(final long accountId, final SortingCondition sortingCondition) {

        return myBookmarkPort.findMyBookmarkFeeds(accountId)
                             .stream()
                             .sorted((bookmark1, bookmark2) -> {
                                 if (sortingCondition == null || sortingCondition.name().equals("NEWER")) {
                                     return bookmark2.getUpdatedAt().compareTo(bookmark1.getUpdatedAt());
                                 }

                                 return bookmark1.getUpdatedAt().compareTo(bookmark2.getUpdatedAt());
                             })
                             .map((bookmarkFeeds) -> {

                                 final Feed savedFeed = bookmarkFeeds.getFeed();
                                 final Long specialReviewId = savedFeed.getSpecialReviewId();

                                 final ExternalMockDataProfile mockDataProfile =
                                         externalMockDataApi.findBySpecialReviewId(specialReviewId);

                                 final SpecialReviewProfile specialReviewProfile = mockDataProfile.getSpecialReviewProfile();
                                 final List<ImageInfo> imageInfos = specialReviewProfile.getImageInfos();

                                 return ThumbnailProfile.builder()
                                                        .imageUrl(imageInfos.get(imageInfos.size() - 1).getImageUrl())
                                                        .recommendCount(savedFeed.getRecommendCount())
                                                        .description(specialReviewProfile.getDescriptionComplete())
                                                        .isRecommended(
                                                                getExistenceOfRecommendQuery.isAccountRecommendFeed(
                                                                        accountId,
                                                                        savedFeed.getId()))
                                                        .feedId(savedFeed.getId())
                                                        .build();
                             })
                             .collect(Collectors.toList());
    }
}
