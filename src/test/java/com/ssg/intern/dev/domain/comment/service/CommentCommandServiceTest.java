package com.ssg.intern.dev.domain.comment.service;

import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentRegisterRequest;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentCommandServiceTest {

    @Autowired
    private CommentCommandService commentCommandService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Test
    @DisplayName("댓글 생성 테스트")
    void createCommentTest() {
        //given
        //when
        commentCommandService.createComment(1L, 2L,
                CommentRegisterRequest.builder().content("댓글입니다").build()
        );
        Comment result = commentRepository.findById(1L).orElseThrow();

        //then
        assertThat(result.getContent()).isEqualTo("댓글입니다");
        assertThat(result.getAccountId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateCommentTest() {
        //given
        Comment comment = commentRepository.save(dummyComment());

        //when
        commentCommandService.updateComment(comment.getId(), 1L,
                CommentRegisterRequest.builder().content("댓글 수정합니다").build()
        );
        Comment result = commentRepository.findById(comment.getId()).orElseThrow();

        //then
        assertThat(result.getContent()).isEqualTo("댓글 수정합니다");
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteTest() {
        //given
        Comment comment = commentRepository.save(dummyComment());

        //when
        commentCommandService.deleteComment(comment.getId(), 1L);

        //then
        assertThatThrownBy(() -> commentRepository.findById(comment.getId()).orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }


    @Test
    @DisplayName("댓글 신고 테스트 - reportCount 증가되고 3번 이상이면 댓글 삭제되는지 확인")
    void reportCommentTest() {
        //given
        Comment comment = commentRepository.save(dummyComment());

        //when
        commentCommandService.reportComment(comment.getId());
        commentCommandService.reportComment(comment.getId());
        Comment result = commentRepository.findById(comment.getId()).orElseThrow();

        //then
        assertThat(result.getReportCount()).isEqualTo(2);

        //when
        commentCommandService.reportComment(comment.getId());

        //then
        assertThatThrownBy(() -> commentRepository.findById(comment.getId()).orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }

    private Comment dummyComment() {
        return Comment.of(feedRepository.findById(1L).orElseThrow(), "content", 1L);
    }

}