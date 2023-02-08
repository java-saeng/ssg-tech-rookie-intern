package com.ssg.intern.dev.recommend.adapter.in.web;

import com.ssg.intern.dev.common.WebAdapter;
import com.ssg.intern.dev.recommend.application.port.in.AddRecommendUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@WebAdapter
@RestController
public class AddRecommendController {

    private final AddRecommendUseCase addRecommendUseCase;

    @PostMapping("/{account-id}/feeds/{feed-id}/recommends")
    public void plusRecommend(@PathVariable("account-id") long accountId,
                              @PathVariable("feed-id") long feedId) {

        addRecommendUseCase.addRecommendToFeed(accountId, feedId);
    }
}
