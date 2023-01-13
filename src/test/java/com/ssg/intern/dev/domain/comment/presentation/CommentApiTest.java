package com.ssg.intern.dev.domain.comment.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.intern.dev.domain.comment.dao.CommentSingleDao;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentSelectResponse;
import com.ssg.intern.dev.domain.comment.service.CommentCommandService;
import com.ssg.intern.dev.domain.comment.service.CommentQueryService;
import com.ssg.intern.dev.domain.feed.entity.Feed;
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
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static com.ssg.intern.ApiDocumentUtils.getDocumentRequest;
import static com.ssg.intern.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
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

    @MockBean
    private CommentCommandService commentCommandService;

    @Test
    @DisplayName("댓글 조회")
    void getCommentsTest() throws Exception {
        //given
        CommentSelectResponse response = dummyCommentResponse();
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

    private Comment dummyComment() {
        Feed feed = Feed.from(1L);
        return Comment.of(feed, "댓글입니당", 2L);
    }

    private CommentSelectResponse dummyCommentResponse() {
        return new CommentSelectResponse(1L,
                List.of(new CommentSingleDao("wldnjs@ssg.com", "댓글입니당", LocalDateTime.now())));
    }
}