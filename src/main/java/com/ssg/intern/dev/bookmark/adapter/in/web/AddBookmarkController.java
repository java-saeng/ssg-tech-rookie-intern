package com.ssg.intern.dev.bookmark.adapter.in.web;

import com.ssg.intern.dev.bookmark.application.port.in.BufferUseCase;
import com.ssg.intern.dev.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
public class AddBookmarkController {

    private final BufferUseCase bufferUseCase;

    public AddBookmarkController(@Qualifier("bookmarkBuffer") final BufferUseCase bufferUseCase) {
        this.bufferUseCase = bufferUseCase;
    }

    @PostMapping("/{account-id}/feeds/{feed-id}/bookmarks")
    public void plusBookmark(@PathVariable("account-id") long accountId,
                             @PathVariable("feed-id") long feedId) {
        bufferUseCase.bufferCaching(accountId, feedId);
    }
}
