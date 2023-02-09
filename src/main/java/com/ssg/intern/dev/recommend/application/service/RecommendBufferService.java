package com.ssg.intern.dev.recommend.application.service;

import com.ssg.intern.dev.bookmark.application.port.in.BufferUseCase;
import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.global.buffer.BufferStatus;
import com.ssg.intern.dev.global.buffer.ConcurrentMapBuffer;
import com.ssg.intern.dev.recommend.application.port.in.AddRecommendUseCase;
import com.ssg.intern.dev.recommend.application.port.in.CancelRecommendUseCase;
import com.ssg.intern.dev.recommend.application.port.out.LoadRecommendPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional
@Qualifier("recommendBuffer")
public class RecommendBufferService implements BufferUseCase {

    private final LoadRecommendPort loadRecommendPort;
    private final AddRecommendUseCase addRecommendUseCase;
    private final CancelRecommendUseCase cancelRecommendUseCase;
    private final ConcurrentMapBuffer buffer;

    public RecommendBufferService(final LoadRecommendPort loadRecommendPort,
                                  final AddRecommendUseCase addRecommendUseCase,
                                  final CancelRecommendUseCase cancelRecommendUseCase) {
        this.loadRecommendPort = loadRecommendPort;
        this.addRecommendUseCase = addRecommendUseCase;
        this.cancelRecommendUseCase = cancelRecommendUseCase;
        buffer = new ConcurrentMapBuffer();
    }

    @Override
    public void bufferCaching(final long accountId, final long feedId) {
        if (buffer.isBufferActivityInBuffer(feedId, accountId)) {
            buffer.put(feedId, accountId, false);
            return;
        }

        loadRecommendPort.findRecommendByFeedAndAccount(accountId, feedId)
                         .ifPresentOrElse((recommend) -> {
                                              buffer.put(feedId, accountId, recommend.isRecommended());
                                          },
                                          () -> {
                                              buffer.put(feedId, accountId, false);
                                          }
                         );
    }

    @Override
    public void bufferFlush() {

        final List<BufferStatus> flushCandidate = buffer.flush();

        for (BufferStatus bufferStatus : flushCandidate) {
            final long accountId = bufferStatus.getAccountId();
            final long feedId = bufferStatus.getFeedId();
            if (bufferStatus.isDeleteFlag()) {
                cancelRecommendUseCase.cancelRecommendToFeed(accountId, feedId);
            } else {
                addRecommendUseCase.addRecommendToFeed(accountId, feedId);
            }
        }
    }
}
