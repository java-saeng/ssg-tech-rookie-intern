package com.ssg.intern.dev.feed.adapter.out.persistence;

import com.ssg.intern.dev.common.PersistenceAdapter;
import com.ssg.intern.dev.feed.application.port.out.LoadSingleFeedPort;
import com.ssg.intern.dev.feed.domain.Feed;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class FeedPersistenceAdapter implements LoadSingleFeedPort {

    private final FeedJpaRepository feedJpaRepository;

    @Override
    public Optional<Feed> findById(final long feedId) {
        return feedJpaRepository.findById(feedId);
    }

    @Override
    public Optional<Feed> findBySpecialReviewId(final long specialReviewId) {
        return feedJpaRepository.findBySpecialReviewId(specialReviewId);
    }
}
