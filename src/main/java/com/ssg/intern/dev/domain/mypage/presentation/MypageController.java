package com.ssg.intern.dev.domain.mypage.presentation;

import com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse;
import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.domain.mypage.service.MypageQueryService;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MypageController {

    private final MypageQueryService mypageQueryService;

    @GetMapping("/me/thumbnails")
    public String getThumbnails(@RequestParam(value = "sort", required = false) SortingCondition sortingCondition,
                                Model model) {

        if(sortingCondition==null) {
            sortingCondition = SortingCondition.NEWER;
        }

        final BookmarkProfileResponse response = mypageQueryService.getThumbnails(1L, sortingCondition);
        model.addAttribute("thumbnails", response.getThumbnails());
        model.addAttribute("bookmarkCount", response.getBookmarkTotalCount());

        return "/mypage/bookmark";
    }

    @GetMapping("/me")
    public String getMyFeeds(@RequestParam(value = "sort", required = false) SortingCondition sortingCondition,
                             Model model) {

        if(sortingCondition==null) {
            sortingCondition = SortingCondition.NEWER;
        }

        final MyReviewProfileResponse response = mypageQueryService.getMyFeeds(1L, sortingCondition);

        model.addAttribute("myReviews", response.getReviews());
        model.addAttribute("totalReviewCount", response.getTotalReviewCount());

        return "/mypage/mypage_review";
    }

}
