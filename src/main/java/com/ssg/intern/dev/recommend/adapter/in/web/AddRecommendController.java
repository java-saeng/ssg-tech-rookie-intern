package com.ssg.intern.dev.recommend.adapter.in.web;

import com.ssg.intern.dev.bookmark.application.port.in.BufferUseCase;
import com.ssg.intern.dev.common.WebAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
public class AddRecommendController {

    private final BufferUseCase bufferUseCase;

    public AddRecommendController(@Qualifier("recommendBuffer") final BufferUseCase bufferUseCase) {
        this.bufferUseCase = bufferUseCase;
    }

    @PostMapping("/{account-id}/feeds/{feed-id}/recommends")
    public void plusRecommend(@PathVariable("account-id") long accountId,
                              @PathVariable("feed-id") long feedId) {

        bufferUseCase.bufferCaching(accountId, feedId);
    }
}
