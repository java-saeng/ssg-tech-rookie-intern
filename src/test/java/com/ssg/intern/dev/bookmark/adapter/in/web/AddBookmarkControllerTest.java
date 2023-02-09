package com.ssg.intern.dev.bookmark.adapter.in.web;

import com.ssg.intern.common.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@DisplayName("AddBookmarkController Unit Test")
class AddBookmarkControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("plusBookmark() : 북마크를 하는 요청을 성공적으로 보낼 수 있다.")
    void test_plusBookmark_status_ok() {

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
                .post("/{account-id}/feeds/{feed-id}/bookmarks", 1, 1)
                .then();
    }
}