package com.ssg.intern.dev.domain.feed.dao;

import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.global.SortingCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedRepositoryCustom {

    public Page<Feed> findAllFeeds(Pageable pageable);
}
