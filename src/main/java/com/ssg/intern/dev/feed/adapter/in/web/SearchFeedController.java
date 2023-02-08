package com.ssg.intern.dev.feed.adapter.in.web;

import com.ssg.intern.dev.common.FeedSearchingConditionRequest;
import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.common.WebAdapter;
import com.ssg.intern.dev.feed.application.port.in.SearchFeedQuery;
import com.ssg.intern.dev.feed.domain.FeedProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@Controller
public class SearchFeedController {

    private final SearchFeedQuery searchFeedQuery;

    @GetMapping("/{account-id}/feeds")
    public String searchSpecificFeeds(Pageable pageable, FeedSearchingConditionRequest request, Model model,
                                      @PathVariable("account-id") long accountId) {

        final List<FeedProfile> feeds = searchFeedQuery.searchFeeds(pageable, request, accountId);
        final HashTagProfile hashTags = searchFeedQuery.findTop10HashTag();

        model.addAttribute("feeds", feeds);
        model.addAttribute("hashtags", hashTags);

        return "feed/feed";
    }
}
