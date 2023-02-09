package com.ssg.intern.dev.feed.adapter.in.web;

import com.ssg.intern.dev.common.WebAdapter;
import com.ssg.intern.dev.feed.application.port.in.GetMyFeedQuery;
import com.ssg.intern.dev.feed.domain.MyFeedProfile;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class GetMyFeedApi {
    private final GetMyFeedQuery getMyFeedQuery;

    @GetMapping("/{account-id}/me")
    public List<MyFeedProfile> getMyFeeds(@RequestParam(value = "sort", required = false) SortingCondition sortingCondition,
                             @PathVariable("account-id") long accountId,
                             Model model) {

        return getMyFeedQuery.getMyFeeds(accountId, sortingCondition);
    }
}
