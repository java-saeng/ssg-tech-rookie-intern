package com.ssg.intern.dev.domain.recommend.service;

import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.domain.recommend.dao.RecommendRepository;
import com.ssg.intern.dev.domain.recommend.entity.Recommend;
import com.ssg.intern.dev.global.buffer.BufferStatus;
import com.ssg.intern.dev.global.buffer.ConcurrentMapBuffer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class RecommendCommandService {

    private final RecommendRepository recommendRepository;
    private final FeedRepository feedRepository;
    private final RecommendQueryService recommendQueryService;
    private final ConcurrentMapBuffer concurrentMapBuffer;

    public RecommendCommandService(final RecommendRepository recommendRepository, final FeedRepository feedRepository,
                                   final RecommendQueryService recommendQueryService) {
        this.recommendRepository = recommendRepository;
        this.feedRepository = feedRepository;
        this.recommendQueryService = recommendQueryService;
        concurrentMapBuffer = new ConcurrentMapBuffer();
    }

    public void addRecommendToFeedByFeedId(final long accountId, final long feedId) {

        recommendRepository.findRecommendByFeedAndAccount(feedId, accountId)
                           .ifPresentOrElse(
                                   (recommend -> {

                                       final Feed savedFeed = recommend.getFeed();

                                       if (!recommend.isRecommended()) {
                                           savedFeed.increaseRecommend();
                                           recommend.addRecommend();
                                       }

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

    public void cancelRecommendToFeedByFeedId(final long accountId, final long feedId) {

        recommendRepository.findRecommendByFeedAndAccount(feedId, accountId)
                           .ifPresent(
                                   (recommend -> {

                                       if (recommend.isRecommended()) {
                                           recommend.cancelRecommend();
                                           recommend.getFeed().decreaseRecommend();
                                       }
                                   })
                           );
    }

    public void bufferFlush() {

        final List<BufferStatus> flushCandidate = concurrentMapBuffer.flush();

        for (final BufferStatus bufferStatus : flushCandidate) {
            if (bufferStatus.isDeleteFlag()) {
                cancelRecommendToFeedByFeedId(bufferStatus.getAccountId(), bufferStatus.getFeedId());
            } else {
                addRecommendToFeedByFeedId(bufferStatus.getAccountId(), bufferStatus.getFeedId());
            }
        }
    }

    public void bufferCaching(final long accountId, final long feedId) {

        if (concurrentMapBuffer.isBufferActivityInBuffer(feedId, accountId)) {
            concurrentMapBuffer.put(feedId, accountId, false);
            return;
        }

        recommendQueryService.findBookmarkByAccountIdAndFeedId(accountId, feedId)
                            .ifPresentOrElse((recommend) -> {
                                concurrentMapBuffer.put(feedId, accountId, recommend.isRecommended());
                            }, () -> {
                                concurrentMapBuffer.put(feedId, accountId, false);
                            });
    }
}
