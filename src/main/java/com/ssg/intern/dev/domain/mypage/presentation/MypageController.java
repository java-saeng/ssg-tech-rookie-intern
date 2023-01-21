package com.ssg.intern.dev.domain.mypage.presentation;

import com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse;
import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.domain.mypage.service.MypageCommandService;
import com.ssg.intern.dev.domain.mypage.service.MypageQueryService;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Controller
public class MypageController {

    private final MypageQueryService mypageQueryService;
    private final MypageCommandService mypageCommandService;

    @GetMapping("/me/thumbnails")
    public String getThumbnails(@RequestParam("sorting") String sortingCondition,
                                @RequestHeader(value = "Authorization") Long accountId,
                                Model model) {
        final BookmarkProfileResponse response = mypageQueryService.getThumbnails(accountId,
                SortingCondition.valueOf(
                        sortingCondition));
        model.addAttribute("thumbnails", response.getThumbnails());
        model.addAttribute("bookmarkCount", response.getBookmarkTotalCount());

        return "/mypage/bookmark";
    }

    @PatchMapping("/me/feeds/{feed-id}/comments/block")
    public void modifyCommentBlocked(@PathVariable("feed-id") long feedId) {
        mypageCommandService.changeCommentBlocked(feedId);
    }

    @GetMapping("/me")
    public String getMyFeeds(@RequestParam("sorting") String sortingCondition,
                             @RequestHeader(value = "Authorization") Long accountId,
                             Model model) {
        final MyReviewProfileResponse response = mypageQueryService.getMyFeeds(accountId,
                SortingCondition.valueOf(
                        sortingCondition));

        model.addAttribute("myReviews", response.getReviews());
        model.addAttribute("totalReviewCount", response.getTotalReviewCount());

        return "/mypage/mypage_review";
    }

}
