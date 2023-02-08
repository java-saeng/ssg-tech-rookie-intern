package com.ssg.intern.dev.bookmark.adapter.in.web;

import com.ssg.intern.dev.bookmark.application.port.in.GetThumbnailQuery;
import com.ssg.intern.dev.bookmark.domain.ThumbnailProfile;
import com.ssg.intern.dev.common.WebAdapter;
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
public class GetThumbnailController {

    private final GetThumbnailQuery getThumbnailQuery;

    @GetMapping("/{account-id}/me/thumbnails")
    public String getThumbnails(@RequestParam(required = false, value = "sort") SortingCondition sortingCondition,
                                @PathVariable("account-id") long accountId,
                                Model model) {

        final List<ThumbnailProfile> result = getThumbnailQuery.getThumbnails(accountId, sortingCondition);

        model.addAttribute("thumbnails", result);
        model.addAttribute("bookmarkCount", result.size());

        return "mypage/bookmark";
    }
}
