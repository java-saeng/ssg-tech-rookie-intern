package com.ssg.intern.dev.domain.recommend.dao;

import com.ssg.intern.dev.domain.recommend.entity.Recommend;

import java.util.Optional;

public interface RecommendRepositoryCustom {

    Optional<Recommend> findRecommendByFeedAndAccount(long feedId, long accountId);
}
