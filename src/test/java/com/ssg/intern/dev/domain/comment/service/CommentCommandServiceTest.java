package com.ssg.intern.dev.domain.comment.service;

import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
//        commentCommandService.createComment(1L, 2L, );
        //then
    }

    @BeforeEach
    void test() {
//        Feed feed = feedRepository.findById(1L).orElseThrow();
//        commentRepository.save(
//                Comment.of(feed, "comment content", 1L)
//        );
    }

    @Test
    @DisplayName("댓글 신고 테스트 - reportCount 증가되고 3번 이상이면 댓글 삭제되는지 확인")
    void reportCommentTest() {
        //given
        Feed feed = feedRepository.findById(1L).orElseThrow();
        commentRepository.save(
                Comment.of(feed, "comment content", 1L)
        );

        //when
        commentCommandService.reportComment(1L);
        commentCommandService.reportComment(1L);

        //then
        Comment comment = commentRepository.findById(1L).orElseThrow();
        assertThat(comment.getReportCount()).isEqualTo(2);

        //when
        commentCommandService.reportComment(1L);

        //then
        assertThatThrownBy(() -> commentRepository.findById(1L).orElseThrow())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("");
    }


}