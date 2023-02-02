package com.ssg.intern.dev.domain.feed.presentation;

import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedSearchingConditionRequest;
import com.ssg.intern.dev.domain.feed.service.FeedQueryService;
import com.ssg.intern.mock.domain.hashtag.dao.HashTagRepository;
import com.ssg.intern.mock.domain.hashtag.entity.HashTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FeedController {

    private final FeedQueryService feedQueryService;
    private final HashTagRepository hashTagRepository;

    @GetMapping("/feeds")
    public String searchSpecificFeed(Pageable pageable, FeedSearchingConditionRequest request, Model model) {
        final List<FeedProfileResponse> result = feedQueryService.showSatisfiedConditionFeeds(pageable, request);
        final List<HashTag> hashTags =  hashTagRepository.findDistinctTop10ByOrderByIdAsc();

        model.addAttribute("feeds", result);
        model.addAttribute("hashtags", hashTags);

        return "feed/feed";
    }

    @GetMapping("/feeds/{feed-id}")
    public String searchOneFeed(@PathVariable("feed-id") long feedId, Model model) {
        final FeedProfileResponse response = feedQueryService.showOneFeed(feedId);
        final List<HashTag> hashTags =  hashTagRepository.findDistinctTop10ByOrderByIdAsc();

        model.addAttribute("feed", response);
        model.addAttribute("hashtags", hashTags);

        return "feed/feed_one";
    }
}
