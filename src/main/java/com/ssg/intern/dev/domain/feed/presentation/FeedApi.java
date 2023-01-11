package com.ssg.intern.dev.domain.feed.presentation;

import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileConditionRequest;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse;
import com.ssg.intern.dev.domain.feed.service.FeedQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public List<FeedProfileResponse> searchAllFeed(@ModelAttribute FeedProfileConditionRequest request) {
        return feedQueryService.findFeedByCondition(request);
    }

    @GetMapping("/feeds/{feed-id}")
    public FeedProfileResponse searchOneFeed(@PathVariable("feed-id") long feedId) {
        return feedQueryService.showOneFeed(feedId);
    }
}
