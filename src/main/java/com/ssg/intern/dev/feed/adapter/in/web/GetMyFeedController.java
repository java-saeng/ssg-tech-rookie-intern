package com.ssg.intern.dev.feed.adapter.in.web;

import com.ssg.intern.dev.common.WebAdapter;
import com.ssg.intern.dev.feed.application.port.in.GetMyFeedQuery;
import com.ssg.intern.dev.feed.domain.MyFeedProfile;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@Controller
public class GetMyFeedController {

    private final GetMyFeedQuery getMyFeedQuery;

    @GetMapping("/{account-id}/me")
    public String getMyFeeds(@RequestParam(value = "sort", required = false) SortingCondition sortingCondition,
                             @PathVariable("account-id") long accountId,
                             Model model) {

        final List<MyFeedProfile> myFeeds = getMyFeedQuery.getMyFeeds(accountId, sortingCondition);

        model.addAttribute("myReviews", myFeeds);
        model.addAttribute("totalReviewCount", myFeeds.size());

        return "/mypage/mypage_review";
    }
}
