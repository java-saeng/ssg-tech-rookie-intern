package com.ssg.intern.dev.domain.feed.presentation;

import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedSearchingConditionRequest;
import com.ssg.intern.dev.domain.feed.service.FeedQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FeedApi {

    private final FeedQueryService feedQueryService;

    @GetMapping("/feeds")
    public List<FeedProfileResponse> searchAllFeed(Pageable pageable) {
        return feedQueryService.showFeedsSortedByCondition(pageable);
    }

    @GetMapping("/feeds/{feed-id}")
    public FeedProfileResponse searchOneFeed(@PathVariable("feed-id") long feedId) {
        return feedQueryService.showOneFeed(feedId);
    }

    @GetMapping("/feeds/search")
    public List<FeedProfileResponse> searchSpecificFeed(Pageable pageable, FeedSearchingConditionRequest request) {
        return feedQueryService.showSatisfiedConditionFeeds(pageable, request);
    }
}
