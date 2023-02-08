package com.ssg.intern.dev.recommend.application.service;

import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.feed.application.port.out.LoadSingleFeedPort;
import com.ssg.intern.dev.feed.domain.Feed;
import com.ssg.intern.dev.recommend.application.port.in.AddRecommendUseCase;
import com.ssg.intern.dev.recommend.application.port.out.AddRecommendPort;
import com.ssg.intern.dev.recommend.application.port.out.LoadRecommendPort;
import com.ssg.intern.dev.recommend.domain.Recommend;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@UseCase
@RequiredArgsConstructor
@Transactional
public class AddRecommendService implements AddRecommendUseCase {

    private final LoadRecommendPort loadRecommendPort;
    private final AddRecommendPort addRecommendPort;
    private final LoadSingleFeedPort loadSingleFeedPort;


    @Override
    public void addRecommendToFeed(final long accountId, final long feedId) {

        loadRecommendPort.findRecommendByFeedAndAccount(accountId, feedId)
                         .ifPresentOrElse(
                                 (recommend -> {

                                     final Feed savedFeed = recommend.getFeed();

                                     if (!recommend.isRecommended()) {
                                         savedFeed.increaseRecommend();
                                         recommend.addRecommend();
                                     }

                                 }),

                                 () -> {
                                     final Feed savedFeed = loadSingleFeedPort.findById(feedId)
                                                                              .orElseThrow(
                                                                                      EntityNotFoundException::new);

                                     final Recommend recommend = Recommend.of(accountId, true, savedFeed);

                                     savedFeed.increaseRecommend();

                                     addRecommendPort.addRecommendToFeed(recommend);
                                 }
                         );
    }
}
