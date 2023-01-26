package com.ssg.intern.dev.domain.recommend.service;

import com.ssg.intern.dev.domain.recommend.dao.RecommendRepository;
import com.ssg.intern.dev.domain.recommend.entity.Recommend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendQueryService {

    private final RecommendRepository recommendRepository;

    public boolean isAccountRecommendFeed(long accountId, long feedId) {

        final Optional<Recommend> savedRecommend = recommendRepository.findRecommendByFeedAndAccount(feedId, accountId);

        if (savedRecommend.isEmpty()) {
            return false;
        }

        final Recommend recommend = savedRecommend.get();

        return recommend.isRecommended();
    }
}
