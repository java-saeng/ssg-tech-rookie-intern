package com.ssg.intern.dev.domain.feed.service;

import com.ssg.intern.dev.domain.feed.presentation.model.FeedProfileResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DisplayName("FeedQueryService Integration Test")
class FeedQueryServiceTest {

    @Autowired
    FeedQueryService feedQueryService;

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