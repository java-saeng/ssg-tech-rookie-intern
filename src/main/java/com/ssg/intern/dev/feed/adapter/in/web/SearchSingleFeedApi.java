package com.ssg.intern.dev.feed.adapter.in.web;

import com.ssg.intern.dev.common.WebAdapter;
import com.ssg.intern.dev.feed.application.port.in.SearchSingleFeedQuery;
import com.ssg.intern.dev.feed.domain.FeedProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SearchSingleFeedApi {

    private final SearchSingleFeedQuery searchSingleFeedQuery;

    @GetMapping("/{account-id}/feeds/{feed-id}")
    public FeedProfile searchOneFeed(@PathVariable("account-id") long accountId,
                                     @PathVariable("feed-id") long feedId) {

        return searchSingleFeedQuery.searchSingleFeed(accountId, feedId);
    }
}
