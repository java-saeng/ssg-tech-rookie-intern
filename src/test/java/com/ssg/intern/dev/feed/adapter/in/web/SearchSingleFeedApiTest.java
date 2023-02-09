package com.ssg.intern.dev.feed.adapter.in.web;

import com.ssg.intern.common.BaseControllerTest;
import com.ssg.intern.dev.external.speical_review.domain.CookTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

class SearchSingleFeedApiTest extends BaseControllerTest {

    @Test
    @DisplayName("searchOneFeed() : feed를 단건 조회할 수 있다.")
    void test_searchOneFeed_status_ok() {

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                                 pathParameters(
                                         parameterWithName("account-id").description("account id"),
                                         parameterWithName("feed-id").description("feed id"))
                        )
                )
                .log().all()
                .when()
                .get("/api/{account-id}/feeds/{feed-id}", 1, 1)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}