package com.ssg.intern.dev.domain.feed.service;

import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileConditionRequest;
import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse;
import com.ssg.intern.dev.global.SortingCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("FeedQueryService Integration Test")
class FeedQueryServiceTest {

    @Autowired
    FeedQueryService feedQueryService;

    @Test
    @DisplayName("findFeedByCondition() : 추천 수에 따라서 Feed 정보를 정렬할 수 있다.")
    void testFindFeedByCondition_sortedByRecommendCount() {
        //when
        final List<FeedProfileResponse> result = feedQueryService.showFeedsSortedByCondition(
                new FeedProfileConditionRequest(SortingCondition.RECOMMENDATION));

        //then
        assertAll(
                () -> assertEquals(result.size(), 20),
                () -> assertThat(result).extracting("feedReactionProfile.recommendCount")
                                        .isSorted());
    }

    @Test
    @DisplayName("findFeedByCondition() : 최근 등록 순에 따라서 Feed 정보를 정렬할 수 있다.")
    void testFindFeedByCondition_sortedByNewer() {
        //when
        final List<FeedProfileResponse> result = feedQueryService.showFeedsSortedByCondition(
                new FeedProfileConditionRequest(SortingCondition.NEWER));

        //then
        assertAll(
                () -> assertEquals(result.size(), 20),
                () -> assertThat(result).extracting("ReviewProfile.createdAt")
                                        .isSorted());
    }

    @Test
    @DisplayName("showOneFeed() : 피드 단건 조회할 수 있다.")
    void testShowOneFeed() {
        //when
        final FeedProfileResponse result = feedQueryService.showOneFeed(1L);

        //then
        assertThat(result.getProductProfile().getImageUrl())
                .isEqualTo("https://sitem.ssgcdn.com/99/43/13/item/1000337134399_i1_1100.jpg");
    }
}