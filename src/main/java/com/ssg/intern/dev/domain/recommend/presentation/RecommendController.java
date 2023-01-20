package com.ssg.intern.dev.domain.recommend.presentation;

import com.ssg.intern.dev.domain.recommend.service.RecommendCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class RecommendController {

    private final RecommendCommandService recommendCommandService;

    @PostMapping("/feeds/{feed-id}/recommends")
    public String plusRecommend(@RequestHeader(HttpHeaders.AUTHORIZATION) long accountId,
                                @PathVariable("feed-id") long feedId) throws IOException {
        recommendCommandService.addRecommendToFeedByFeedId(accountId, feedId);

        return "redirect:/feeds/{feed-id}";
    }

    @DeleteMapping("/feeds/{feed-id}/recommends")
    public String minusRecommend(@RequestHeader(HttpHeaders.AUTHORIZATION) long accountId,
                               @PathVariable("feed-id") long feedId) {
        recommendCommandService.cancelRecommendToFeedByFeedId(accountId, feedId);

        return "redirect:/feeds/{feedId}";
    }
}
