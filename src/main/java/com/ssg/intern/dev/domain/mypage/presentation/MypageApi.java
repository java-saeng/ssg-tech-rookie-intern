package com.ssg.intern.dev.domain.mypage.presentation;

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
    private final MypageQueryService mypageQueryService;

    @GetMapping("/me/thumbnails")
    public BookmarkProfileResponse getThumbnails(@RequestParam String sortingCondition,
                                                 @RequestHeader(value = "Authorization") @NotBlank String accountId) {
        return mypageQueryService.getThumbnails(Long.parseLong(accountId),SortingCondition.valueOf(sortingCondition));
    }

    @PatchMapping("/me/feeds/{feed-id}/comments/block")
    public void modifyCommentBlocked(@PathVariable("feed-id") long feedId) {
        mypageCommandService.changeCommentBlocked(feedId);
    }

    @GetMapping("/me")
    public MyReviewProfileResponse getMyFeeds(@RequestParam("sorting") String sortingCondition,
                                              @RequestHeader(value = "Authorization") @NotBlank String accountId) {
        return mypageQueryService.getMyFeeds(Long.parseLong(accountId), SortingCondition.valueOf(sortingCondition));
    }
}
