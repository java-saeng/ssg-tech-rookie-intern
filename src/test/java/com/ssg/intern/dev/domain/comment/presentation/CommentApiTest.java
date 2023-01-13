package com.ssg.intern.dev.domain.comment.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.intern.dev.domain.comment.dao.CommentSingleDao;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentRegisterRequest;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentSelectResponse;
import com.ssg.intern.dev.domain.comment.service.CommentQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static com.ssg.intern.ApiDocumentUtils.getDocumentRequest;
import static com.ssg.intern.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
class CommentApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentQueryService commentQueryService;

    @Test
    @DisplayName("댓글 조회")
    void getCommentsTest() throws Exception {
        //given
        CommentSelectResponse response = new CommentSelectResponse(1L,
                List.of(CommentSingleDao.builder()
                        .email("wldnjs@ssg.com")
                        .content("댓글입니다")
                        .createdAt(LocalDateTime.now())
                        .build())
        );
        given(commentQueryService.getComments(1L)).willReturn(response);

        //when
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/feeds/{feed-id}/comments", 1L)
                        .accept(MediaType.APPLICATION_JSON));

        //then
        result.andDo(document("comment-select",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("feed-id").description("피드 ID")
                ),
                responseFields(
                        fieldWithPath("commentCount").description("댓글 개수"),
                        fieldWithPath("comments[].email").description("댓글 작성자 이메일"),
                        fieldWithPath("comments[].content").description("댓글 내용"),
                        fieldWithPath("comments[].createdAt").description("댓글 작성 날짜")
                )
        ));
    }

    @Test
    @DisplayName("댓글 작성 API 테스트")
    void createCommentTest() throws Exception {
        //given
        CommentRegisterRequest request = CommentRegisterRequest.builder()
                .content("comment content")
                .build();

        //when
        String requestJson = objectMapper.writeValueAsString(request);

        //then
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/api/feeds/{feed-id}/comments", 1L)
                        .header("Authorization", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(document("comment-create",
                        requestHeaders(
                                HeaderDocumentation.headerWithName("Authorization").description("사용자 ID")
                        ),
                        pathParameters(
                                RequestDocumentation.parameterWithName("feed-id").description("피드 ID")
                        ),
                        requestFields(
                                PayloadDocumentation.fieldWithPath("content").description("작성할 댓글 내용")))
                );
    }

    @Test
    @DisplayName("댓글 수정 API 테스트")
    void updateCommentTest() throws Exception {
        //given
        CommentRegisterRequest request = CommentRegisterRequest.builder()
                .content("comment update content")
                .build();

        //when
        String requestJson = objectMapper.writeValueAsString(request);

        //then
        this.mockMvc.perform(RestDocumentationRequestBuilders.put("/api/comments/{comment-id}", 1L)
                        .header("Authorization", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(document("comment-update",
                        requestHeaders(
                                HeaderDocumentation.headerWithName("Authorization").description("사용자 ID")
                        ),
                        pathParameters(
                                RequestDocumentation.parameterWithName("comment-id").description("댓글 ID")
                        ),
                        requestFields(
                                PayloadDocumentation.fieldWithPath("content").description("수정할 댓글 내용")))
                );
    }

    @Test
    @DisplayName("댓글 삭제 API 테스트")
    void deleteCommentTest() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/comments/{comment-id}", 1L)
                        .header("Authorization", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(document("comment-delete",
                        requestHeaders(
                                HeaderDocumentation.headerWithName("Authorization").description("사용자 ID")
                        ),
                        pathParameters(
                                RequestDocumentation.parameterWithName("comment-id").description("댓글 ID")
                        ))
                );
    }

}