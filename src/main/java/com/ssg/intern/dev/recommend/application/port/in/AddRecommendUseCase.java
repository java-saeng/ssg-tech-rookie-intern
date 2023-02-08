package com.ssg.intern.dev.recommend.application.port.in;

public interface AddRecommendUseCase {

    void addRecommendToFeed(final long accountId, final long feedId);

}
