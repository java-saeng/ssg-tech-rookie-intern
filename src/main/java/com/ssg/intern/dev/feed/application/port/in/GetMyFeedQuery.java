package com.ssg.intern.dev.feed.application.port.in;

import com.ssg.intern.dev.feed.domain.MyFeedProfile;
import com.ssg.intern.dev.global.SortingCondition;

import java.util.List;

public interface GetMyFeedQuery {

    List<MyFeedProfile> getMyFeeds(long accountId, SortingCondition sortingCondition);
}
