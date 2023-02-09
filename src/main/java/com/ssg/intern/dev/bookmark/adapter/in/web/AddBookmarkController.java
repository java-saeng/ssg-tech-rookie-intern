package com.ssg.intern.dev.bookmark.adapter.in.web;

import com.ssg.intern.dev.bookmark.application.port.in.AddBookmarkUseCase;
import com.ssg.intern.dev.bookmark.application.port.in.UsingBookmarkBufferUseCase;
import com.ssg.intern.dev.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@WebAdapter
@RestController
public class AddBookmarkController {

    private final UsingBookmarkBufferUseCase usingBookmarkBufferUseCase;

    @PostMapping("/{account-id}/feeds/{feed-id}/bookmarks")
    public void plusBookmark(@PathVariable("account-id") long accountId,
                             @PathVariable("feed-id") long feedId) {
        usingBookmarkBufferUseCase.bufferCaching(accountId, feedId);
    }
}
