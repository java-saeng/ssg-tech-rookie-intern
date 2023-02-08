package com.ssg.intern.dev.feed.adapter.out.persistence;

import com.ssg.intern.dev.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedJpaRepository extends JpaRepository<Feed, Long>, FeedJpaRepositoryCustom {

    Optional<Feed> findBySpecialReviewId(long specialReviewId);
}
