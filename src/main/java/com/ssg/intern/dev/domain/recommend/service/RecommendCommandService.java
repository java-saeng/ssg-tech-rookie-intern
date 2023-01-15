package com.ssg.intern.dev.domain.recommend.service;

import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.domain.recommend.dao.RecommendRepository;
import com.ssg.intern.dev.domain.recommend.entity.Recommend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendCommandService {

    private final RecommendRepository recommendRepository;
    private final FeedRepository feedRepository;

    public void addRecommendToFeedByFeedId(final long accountId, final long feedId) {

        recommendRepository.findRecommendByFeedAndAccount(feedId, accountId)
                           .ifPresentOrElse(
                                   (recommend -> {

                                       final Feed savedFeed = recommend.getFeed();

                                       savedFeed.increaseRecommend();
                                       recommend.addRecommend();
                                   }),

                                   () -> {
                                       final Feed savedFeed = feedRepository.findById(feedId)
                                                                            .orElseThrow(EntityNotFoundException::new);

                                       final Recommend recommend = Recommend.of(accountId, true, savedFeed);

                                       recommendRepository.save(recommend);
                                       savedFeed.increaseRecommend();
                                   }
                           );
    }

    public long cancelRecommendToFeedByFeedId(final long accountId, final long feedId, final long recommendId) {
        final Recommend recommend = recommendRepository.findById(recommendId)
                                                       .orElseThrow(EntityNotFoundException::new);

        final Feed feed = recommend.getFeed();

        feed.decreaseRecommend();

        recommend.cancelRecommend();

        return feed.getRecommendCount();
    }
}
