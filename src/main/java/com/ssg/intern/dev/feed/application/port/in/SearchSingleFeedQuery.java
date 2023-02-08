package com.ssg.intern.dev.feed.application.port.in;

import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.feed.domain.FeedProfile;

public interface SearchSingleFeedQuery {

    FeedProfile searchSingleFeed(final long accountId, final long feedId);

    HashTagProfile findTop10HashTag();
}
