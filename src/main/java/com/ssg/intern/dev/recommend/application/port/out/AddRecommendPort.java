package com.ssg.intern.dev.recommend.application.port.out;

import com.ssg.intern.dev.recommend.domain.Recommend;

public interface AddRecommendPort {

    void addRecommendToFeed(Recommend recommend);
}
