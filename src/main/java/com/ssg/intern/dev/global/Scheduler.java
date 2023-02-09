package com.ssg.intern.dev.global;

import com.ssg.intern.dev.bookmark.application.service.BookmarkBufferService;
import com.ssg.intern.dev.recommend.application.service.RecommendBufferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final BookmarkBufferService bookmarkBufferService;
    private final RecommendBufferService recommendBufferService;

    @Scheduled(cron = "0/15 * * * * *")
    public void flushBookmarkBuffer() {
        log.info("bookmarkBuffer Flush");
        bookmarkBufferService.bufferFlush();
    }

    @Scheduled(cron = "0/15 * * * * *")
    public void flushRecommendBuffer() {
        log.info("recommendBuffer Flush");
        recommendBufferService.bufferFlush();
    }
}
