package com.ssg.intern.dev.feed.adapter.in.web;

import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.common.WebAdapter;
import com.ssg.intern.dev.feed.application.port.in.SearchSingleFeedQuery;
import com.ssg.intern.dev.feed.domain.FeedProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@WebAdapter
@RequiredArgsConstructor
@Controller
public class SearchSingleFeedController {

    private final SearchSingleFeedQuery searchSingleFeedQuery;

    @GetMapping("/{account-id}/feeds/{feed-id}")
    public String searchOneFeed(@PathVariable("account-id") long accountId,
                                @PathVariable("feed-id") long feedId,
                                Model model) {

        final FeedProfile feed = searchSingleFeedQuery.searchSingleFeed(accountId, feedId);
        final HashTagProfile hashTags = searchSingleFeedQuery.findTop10HashTag();

        model.addAttribute("feed", feed);
        model.addAttribute("hashtags", hashTags);

        return "feed/feed_one";
    }
}
