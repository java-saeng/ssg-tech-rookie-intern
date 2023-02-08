package com.ssg.intern.dev.recommend.application.port.in;

public interface GetExistenceOfRecommendQuery {

    boolean isAccountRecommendFeed(final long accountId, final long feedId);
}
