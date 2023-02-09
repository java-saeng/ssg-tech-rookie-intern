package com.ssg.intern.dev.global;

import com.ssg.intern.dev.bookmark.application.port.in.UsingBookmarkBufferUseCase;
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

    private final UsingBookmarkBufferUseCase usingBookmarkBufferUseCase;
//    private final RecommendCommandService recommendCommandService;

    @Scheduled(cron = "0/15 * * * * *")
    public void flushBookmarkBuffer() {
        log.info("bookmarkBuffer Flush");
        usingBookmarkBufferUseCase.bufferFlush();
    }

//    @Scheduled(cron = "0/15 * * * * *")
//    public void flushRecommendBuffer() {
//        log.info("recommendBuffer Flush");
//        recommendCommandService.bufferFlush();
//    }
}
