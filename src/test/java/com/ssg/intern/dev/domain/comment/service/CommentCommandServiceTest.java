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

    @BeforeEach
    void before() {
        Feed feed = feedRepository.findById(1L).orElseThrow();
        Comment comment = Comment.of(feed, "comment content", 1L);
        commentRepository.save(comment);
    }
    @Test
    @DisplayName("댓글 신고 테스트 - reportCount 증가?")
    void reportCommentCountTest() {
        //given
        //when
        commentCommandService.reportComment(1L);
        commentCommandService.reportComment(1L);

        //then
        Comment comment = commentRepository.findById(1L).orElseThrow();
        assertThat(comment.getReportCount()).isEqualTo(2);
    }
    @Test
    @DisplayName("댓글 신고 3번 이상이면 삭제 되는지 테스트")
    void reportCommentDeleteTest() {
        //given
        //when
        commentCommandService.reportComment(1L);
        commentCommandService.reportComment(1L);
        commentCommandService.reportComment(1L);

        //then
        assertThatThrownBy(() -> commentRepository.findById(1L).orElseThrow())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("");
    }
}