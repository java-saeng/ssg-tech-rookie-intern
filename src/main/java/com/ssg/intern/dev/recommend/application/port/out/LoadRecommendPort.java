package com.ssg.intern.dev.recommend.application.port.out;

import com.ssg.intern.dev.recommend.domain.Recommend;

import java.util.Optional;

public interface LoadRecommendPort {

    Optional<Recommend> findRecommendByFeedAndAccount(final long accountId, final long feedId);
}
