package com.ssg.intern.dev.domain.feed.dao;

import com.ssg.intern.dev.domain.feed.entity.Feed;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long>, FeedRepositoryCustom {

    @Override
    @EntityGraph(attributePaths = {"comments"})
    Optional<Feed> findById(Long feedID);
}
