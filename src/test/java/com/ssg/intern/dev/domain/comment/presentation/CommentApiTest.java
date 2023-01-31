package com.ssg.intern.dev.domain.comment.presentation;

import com.ssg.intern.common.BaseControllerTest;
import com.ssg.intern.dev.domain.comment.dao.CommentSingleDao;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentSelectResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document; //꼭 이걸로 해야함!
import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

class CommentApiTest extends BaseControllerTest {

    @Test
    @DisplayName("댓글 조회 테스트")
    void getCommentsTest() {
        CommentSelectResponse response = new CommentSelectResponse(1L,
                List.of(CommentSingleDao.builder()
                        .email("wldnjs@ssg.com")
                        .content("댓글입니다")
                        .createdAt(LocalDateTime.now())
                        .build())
        );

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                                pathParameters(
                                        parameterWithName("feed-id").description("feed id")
                                ),
                                responseFields(fieldWithPath("commentCount").type(JsonFieldType.NUMBER).description("댓글 개수"),
                                        fieldWithPath("comments").type(JsonFieldType.ARRAY).description("댓글 목록"))
                        )
                )
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Content-type", "application/json")
                .log().all()

                .when()
                .get("/api/feeds/{feed-id}/comments", 1L)

                .then();
    }
}