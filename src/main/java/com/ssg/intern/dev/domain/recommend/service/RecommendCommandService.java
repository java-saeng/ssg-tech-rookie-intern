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

    public long addRecommendToFeedByFeedId(final long accountId, final long feedId,
                                           final Optional<Long> recommendId) {

        final Feed feed = feedRepository.findById(feedId)
                                        .orElseThrow(EntityNotFoundException::new);

        feed.increaseRecommend();

        recommendId.ifPresentOrElse(
                (id) -> {
                    final Recommend savedRecommend = recommendRepository.findById(id)
                                                                        .orElseThrow(EntityNotFoundException::new);

                    savedRecommend.addRecommend();
                },

                () -> {
                    final Recommend recommend = Recommend.of(accountId, true, feed);

                    recommendRepository.save(recommend);
                }
        );

        return feed.getRecommendCount();
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
