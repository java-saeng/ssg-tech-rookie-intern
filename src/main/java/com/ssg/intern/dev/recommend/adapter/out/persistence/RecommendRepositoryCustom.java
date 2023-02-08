package com.ssg.intern.dev.recommend.adapter.out.persistence;

import com.ssg.intern.dev.recommend.domain.Recommend;

import java.util.Optional;

public interface RecommendRepositoryCustom {

    Optional<Recommend> findRecommendByFeedAndAccount(long accountId, long feedId);
}
