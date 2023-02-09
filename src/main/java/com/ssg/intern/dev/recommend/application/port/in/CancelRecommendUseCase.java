package com.ssg.intern.dev.recommend.application.port.in;

public interface CancelRecommendUseCase {

    void cancelRecommendToFeed(final long accountId, final long feedId);
}
