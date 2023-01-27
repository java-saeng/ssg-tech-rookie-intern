package com.ssg.intern.dev.domain.feed.presentation;

import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedSearchingConditionRequest;
import com.ssg.intern.dev.domain.feed.service.FeedQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FeedController {

    private final FeedQueryService feedQueryService;

    @GetMapping("/feeds")
    public String searchAllFeed(Pageable pageable, Model model) {
        final List<FeedProfileResponse> response = feedQueryService.showFeedsSortedByCondition(pageable);
        model.addAttribute("feeds", response);

        return "feed/feed";
    }

    @GetMapping("/feeds/{feed-id}")
    public String searchOneFeed(@PathVariable("feed-id") long feedId, Model model) {
        final FeedProfileResponse response = feedQueryService.showOneFeed(feedId);
        model.addAttribute("feed", response);

        return "feed/feed_one";
    }

    @GetMapping("/feeds/search")
    public String searchSpecificFeed(Pageable pageable, FeedSearchingConditionRequest request, Model model) {
        final List<FeedProfileResponse> result = feedQueryService.showSatisfiedConditionFeeds(pageable,
                                                                                              request);
        model.addAttribute("feeds", result);

        return "feed/feed_list";
    }
}
