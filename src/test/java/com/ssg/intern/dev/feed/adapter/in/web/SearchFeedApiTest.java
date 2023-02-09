package com.ssg.intern.dev.feed.adapter.in.web;

import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import com.ssg.intern.common.BaseControllerTest;
import com.ssg.intern.dev.external.speical_review.domain.CookTime;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

class SearchFeedApiTest extends BaseControllerTest {

    @Test
    @DisplayName("searchSpecificFeeds() : 조건에 맞게 feeds를 조회할 수 있다.")
    void test_plusRecommend_status_ok() throws Exception {

        Map<String, Object> map = new HashMap<>();

        map.put("hashTag", "맛있어");
        map.put("cookTime", CookTime.ONE_HOUR);

        given(this.spec)
                .queryParams(map)
                .filter(document(DEFAULT_RESTDOC_PATH,
                                 pathParameters(
                                         parameterWithName("account-id").description("account id"))
                        )
                )
                .log().all()
                .when()
                .get("/api/{account-id}/feeds", 1)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}