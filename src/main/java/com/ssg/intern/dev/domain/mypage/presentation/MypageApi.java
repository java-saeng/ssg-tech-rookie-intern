package com.ssg.intern.dev.domain.mypage.presentation;

import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.domain.mypage.service.MypageQueryService;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MypageApi {

    private final MypageQueryService mypageQueryService;

    @GetMapping("/me/thumbnails")
    public BookmarkProfileResponse getThumbnails(@RequestParam String sortingCondition,
                                                 @RequestHeader(value = "Authorization") String accountId) {
        return mypageQueryService.getThumbnails(Long.parseLong(accountId),SortingCondition.valueOf(sortingCondition));
    }
}
