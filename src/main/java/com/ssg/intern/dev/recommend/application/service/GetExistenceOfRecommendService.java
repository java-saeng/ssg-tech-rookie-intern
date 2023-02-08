package com.ssg.intern.dev.recommend.application.service;

import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.recommend.application.port.in.GetExistenceOfRecommendQuery;
import com.ssg.intern.dev.recommend.application.port.out.LoadRecommendPort;
import com.ssg.intern.dev.recommend.domain.Recommend;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetExistenceOfRecommendService implements GetExistenceOfRecommendQuery {

    private final LoadRecommendPort loadRecommendPort;

    @Override
    public boolean isAccountRecommendFeed(final long accountId, final long feedId) {

        final Optional<Recommend> savedRecommend = loadRecommendPort.findRecommendByFeedAndAccount(accountId, feedId);

        if (savedRecommend.isEmpty()) {
            return false;
        }

        final Recommend recommend = savedRecommend.get();

        System.out.println("recommend.isRecommended() = " + recommend.isRecommended());

        return recommend.isRecommended();
    }
}
