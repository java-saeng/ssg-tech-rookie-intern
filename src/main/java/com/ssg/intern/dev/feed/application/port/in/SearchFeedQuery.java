package com.ssg.intern.dev.feed.application.port.in;

import com.ssg.intern.dev.common.FeedSearchingConditionRequest;
import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.feed.domain.FeedProfile;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchFeedQuery {

    List<FeedProfile> searchFeeds(final Pageable pageable, final FeedSearchingConditionRequest request,
                                  final long accountId);

    HashTagProfile findTop10HashTag();
}
