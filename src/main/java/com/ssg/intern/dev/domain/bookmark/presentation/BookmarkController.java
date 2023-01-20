package com.ssg.intern.dev.domain.bookmark.presentation;

import com.ssg.intern.dev.domain.bookmark.service.BookmarkCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkCommandService bookmarkCommandService;

    @PostMapping("/feeds/{feed-id}/bookmarks")
    public String plusBookmark(@RequestHeader(HttpHeaders.AUTHORIZATION) long accountId,
                             @PathVariable("feed-id") long feedId) {
        bookmarkCommandService.addBookmarkToFeedByFeedId(accountId, feedId);

        return "redirect:/feeds/{feed-id}";
    }

    @DeleteMapping("/feeds/{feed-id}/bookmarks")
    public String minusBookmark(@RequestHeader(HttpHeaders.AUTHORIZATION) long accountId,
                              @PathVariable("feed-id") long feedId) {
        bookmarkCommandService.cancelBookmarkToFeedByFeedId(accountId, feedId);

        return "redirect:/feeds/{feedId}";
    }
}
