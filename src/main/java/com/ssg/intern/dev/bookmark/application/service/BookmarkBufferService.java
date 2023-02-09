package com.ssg.intern.dev.bookmark.application.service;

import com.ssg.intern.dev.bookmark.application.port.in.AddBookmarkUseCase;
import com.ssg.intern.dev.bookmark.application.port.in.CancelBookmarkUseCase;
import com.ssg.intern.dev.bookmark.application.port.in.BufferUseCase;
import com.ssg.intern.dev.bookmark.application.port.out.LoadBookmarkPort;
import com.ssg.intern.dev.common.UseCase;
import com.ssg.intern.dev.global.buffer.BufferStatus;
import com.ssg.intern.dev.global.buffer.ConcurrentMapBuffer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional
@Qualifier("bookmarkBuffer")
public class BookmarkBufferService implements BufferUseCase {

    private final LoadBookmarkPort loadBookmarkPort;
    private final AddBookmarkUseCase addBookmarkUseCase;
    private final ConcurrentMapBuffer buffer;
    private final CancelBookmarkUseCase cancelBookmarkUseCase;


    public BookmarkBufferService(final LoadBookmarkPort loadBookmarkPort, final AddBookmarkUseCase addBookmarkUseCase,
                                 final CancelBookmarkUseCase cancelBookmarkUseCase) {
        this.loadBookmarkPort = loadBookmarkPort;
        this.addBookmarkUseCase = addBookmarkUseCase;
        this.cancelBookmarkUseCase = cancelBookmarkUseCase;
        this.buffer = new ConcurrentMapBuffer();
    }

    @Override
    public void bufferCaching(final long accountId, final long feedId) {
        if (buffer.isBufferActivityInBuffer(feedId, accountId)) {
            buffer.put(feedId, accountId, false);
            return;
        }

        loadBookmarkPort.findBookmarkByFeedAndAccount(accountId, feedId)
                        .ifPresentOrElse((bookmark) -> {
                                             buffer.put(feedId, accountId, bookmark.isBookmarked());
                                         },
                                         () -> {
                                             buffer.put(feedId, accountId, false);
                                         });
    }

    @Override
    public void bufferFlush() {
        final List<BufferStatus> flushCandidate = buffer.flush();

        for (final BufferStatus bufferStatus : flushCandidate) {
            if (bufferStatus.isDeleteFlag()) {
                cancelBookmarkUseCase.cancelBookmarkToFeed(bufferStatus.getAccountId(), bufferStatus.getFeedId());
            } else {
                addBookmarkUseCase.addBookmarkToFeed(bufferStatus.getAccountId(), bufferStatus.getFeedId());
            }
        }
    }
}
