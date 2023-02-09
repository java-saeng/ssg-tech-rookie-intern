package com.ssg.intern.dev.bookmark.application.service;

import com.ssg.intern.dev.bookmark.application.port.in.CancelBookmarkUseCase;
import com.ssg.intern.dev.bookmark.application.port.out.LoadBookmarkPort;
import com.ssg.intern.dev.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class CancelBookmarkService implements CancelBookmarkUseCase {

    private final LoadBookmarkPort loadBookmarkPort;

    @Override
    public void cancelBookmarkToFeed(final long accountId, final long feedId) {

        loadBookmarkPort.findBookmarkByFeedAndAccount(accountId, feedId)
                .ifPresent((bookmark) -> {
                    bookmark.cancelBookmark();
                    bookmark.getFeed().decreaseBookmark();
                });
    }
}
