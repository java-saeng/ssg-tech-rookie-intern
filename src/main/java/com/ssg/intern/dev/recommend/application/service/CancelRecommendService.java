package com.ssg.intern.dev.recommend.application.service;

import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.recommend.application.port.in.CancelRecommendUseCase;
import com.ssg.intern.dev.recommend.application.port.out.LoadRecommendPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CancelRecommendService implements CancelRecommendUseCase {

    private final LoadRecommendPort loadRecommendPort;

    @Override
    public void cancelRecommendToFeed(final long accountId, final long feedId) {

        loadRecommendPort.findRecommendByFeedAndAccount(accountId, feedId)
                .ifPresent((recommend) -> {
                    recommend.cancelRecommend();
                    recommend.getFeed().decreaseRecommend();
                });
    }
}
