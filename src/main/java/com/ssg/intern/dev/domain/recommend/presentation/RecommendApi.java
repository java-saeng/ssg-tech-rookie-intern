package com.ssg.intern.dev.domain.recommend.presentation;

import com.ssg.intern.dev.domain.recommend.service.RecommendCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecommendApi {

    private final RecommendCommandService recommendCommandService;

    @PostMapping( "/feeds/{feed-id}/recommends")
    public void plusRecommend(@RequestHeader(HttpHeaders.AUTHORIZATION) long accountId,
                              @PathVariable("feed-id") long feedId) {
        recommendCommandService.addRecommendToFeedByFeedId(accountId, feedId);
    }

    @DeleteMapping("/feeds/{feed-id}/recommends/{recommend-id}")
    public long minusRecommend(@RequestHeader(HttpHeaders.AUTHORIZATION) long accountId,
                              @PathVariable("feed-id") long feedId,
                              @PathVariable("recommend-id") long recommendId) {
        return recommendCommandService.cancelRecommendToFeedByFeedId(accountId, feedId, recommendId);
    }
}
