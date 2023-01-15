package com.ssg.intern.dev.domain.bookmark.presentation;

import com.ssg.intern.dev.domain.bookmark.service.BookmarkCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookmarkApi {

    private final BookmarkCommandService bookmarkCommandService;

    @PostMapping("/feeds/{feed-id}/bookmarks")
    public void plusBookmark(@RequestHeader(HttpHeaders.AUTHORIZATION) long accountId,
                              @PathVariable("feed-id") long feedId) {
        bookmarkCommandService.addBookmarkToFeedByFeedId(accountId, feedId);
    }
}
