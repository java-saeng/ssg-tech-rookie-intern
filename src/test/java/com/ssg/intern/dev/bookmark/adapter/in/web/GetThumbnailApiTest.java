package com.ssg.intern.dev.bookmark.adapter.in.web;

import com.ssg.intern.common.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
class GetThumbnailApiTest extends BaseControllerTest {

    @Test
    @DisplayName("북마크한 레시피 조회 api test")
    void testGetThumbnailApi() {
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        pathParameters(
                                parameterWithName("account-id").description("account id")
                        )))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .log().all()
                .when()
                .get("/api/{account-id}/me/thumbnails", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}