package com.ssg.intern.dev.recommend.adapter.in.web;

import com.ssg.intern.common.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

class AddRecommendControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("plusRecommend() : 추천해요를 하는 요청을 성공적으로 보낼 수 있다.")
    void test_plusRecommend_status_ok() {

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                                 pathParameters(
                                         parameterWithName("feed-id").description("feed id"),
                                         parameterWithName("account-id").description("account id")
                                 )
                        )
                )
                .log().all()
                .when()
                .post("/{account-id}/feeds/{feed-id}/recommends", 1, 1)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}