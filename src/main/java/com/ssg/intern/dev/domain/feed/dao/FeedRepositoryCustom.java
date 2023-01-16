package com.ssg.intern.dev.domain.feed.dao;

import com.ssg.intern.dev.domain.feed.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FeedRepositoryCustom {

    Page<Feed> findAllFeeds(Pageable pageable);
}
