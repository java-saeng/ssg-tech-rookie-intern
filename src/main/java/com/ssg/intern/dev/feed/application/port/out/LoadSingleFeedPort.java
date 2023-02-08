package com.ssg.intern.dev.feed.application.port.out;

import com.ssg.intern.dev.feed.domain.Feed;

import java.util.Optional;

public interface LoadSingleFeedPort {

    Optional<Feed> findById(long feedId);

    Optional<Feed> findBySpecialReviewId(long specialReviewId);
}
