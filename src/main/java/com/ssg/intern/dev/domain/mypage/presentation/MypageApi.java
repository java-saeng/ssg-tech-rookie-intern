package com.ssg.intern.dev.domain.mypage.presentation;

import com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse;
import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.domain.mypage.service.MypageQueryService;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MypageApi {

    private final MypageQueryService mypageQueryService;

    @GetMapping("/me/thumbnails")
    public BookmarkProfileResponse getThumbnails(@RequestParam(value = "sort", required = false) SortingCondition sortingCondition,
                                                 @RequestHeader(value = "Authorization") @NotBlank Long accountId) {
        if(sortingCondition==null) {
            sortingCondition = SortingCondition.NEWER;
        }
        return mypageQueryService.getThumbnails(accountId,sortingCondition);
    }

    @GetMapping("/me")
    public MyReviewProfileResponse getMyFeeds(@RequestParam(value = "sort", required = false) SortingCondition sortingCondition,
                                              @RequestHeader(value = "Authorization") @NotBlank Long accountId) {
        if(sortingCondition==null) {
            sortingCondition = SortingCondition.NEWER;
        }
        return mypageQueryService.getMyFeeds(accountId, sortingCondition);
    }
}
