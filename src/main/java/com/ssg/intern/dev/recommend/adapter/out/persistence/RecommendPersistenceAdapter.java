package com.ssg.intern.dev.recommend.adapter.out.persistence;

import com.ssg.intern.dev.common.PersistenceAdapter;
import com.ssg.intern.dev.recommend.application.port.out.AddRecommendPort;
import com.ssg.intern.dev.recommend.application.port.out.LoadRecommendPort;
import com.ssg.intern.dev.recommend.domain.Recommend;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class RecommendPersistenceAdapter implements LoadRecommendPort, AddRecommendPort {

    private final RecommendRepository recommendRepository;


    @Override
    public Optional<Recommend> findRecommendByFeedAndAccount(final long accountId, final long feedId) {
        return recommendRepository.findRecommendByFeedAndAccount(accountId, feedId);
    }

    @Override
    public void addRecommendToFeed(final Recommend recommend) {
        recommendRepository.save(recommend);
    }
}
