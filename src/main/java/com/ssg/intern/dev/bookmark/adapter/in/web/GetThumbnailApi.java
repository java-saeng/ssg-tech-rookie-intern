package com.ssg.intern.dev.bookmark.adapter.in.web;

import com.ssg.intern.dev.bookmark.application.port.in.GetThumbnailQuery;
import com.ssg.intern.dev.bookmark.domain.ThumbnailProfile;
import com.ssg.intern.dev.common.WebAdapter;
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
public class GetThumbnailApi {

    private final GetThumbnailQuery getThumbnailQuery;

    @GetMapping("/{account-id}/me/thumbnails")
    public List<ThumbnailProfile> getThumbnails(@RequestParam(required = false, value = "sort") SortingCondition sortingCondition,
                                @PathVariable("account-id") long accountId,
                                Model model) {

        return getThumbnailQuery.getThumbnails(accountId, sortingCondition);
    }
}
