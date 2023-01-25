package com.ssg.intern.dev.domain.mypage.presentation;

import com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse;
import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.domain.mypage.service.MypageQueryService;
import com.ssg.intern.dev.domain.mypage.service.MypageCommandService;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MypageApi {

    private final MypageQueryService mypageQueryService;
    private final MypageCommandService mypageCommandService;

    @GetMapping("/me/thumbnails")
    public BookmarkProfileResponse getThumbnails(@RequestParam(value = "sorting", required = false) SortingCondition sortingCondition,
                                                 @RequestHeader(value = "Authorization") @NotBlank Long accountId) {
        if(sortingCondition==null) {
            sortingCondition = SortingCondition.NEWER;
        }
        return mypageQueryService.getThumbnails(accountId,sortingCondition);
    }

    @PatchMapping("/me/feeds/{feed-id}/comments/block")
    public void modifyCommentBlocked(@PathVariable("feed-id") long feedId) {
        mypageCommandService.changeCommentBlocked(feedId);
    }

    @GetMapping("/me")
    public MyReviewProfileResponse getMyFeeds(@RequestParam(value = "sorting", required = false) SortingCondition sortingCondition,
                                              @RequestHeader(value = "Authorization") @NotBlank Long accountId) {
        if(sortingCondition==null) {
            sortingCondition = SortingCondition.NEWER;
        }
        return mypageQueryService.getMyFeeds(accountId, sortingCondition);
    }
}
