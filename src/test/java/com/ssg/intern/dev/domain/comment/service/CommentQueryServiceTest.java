//package com.ssg.intern.dev.domain.comment.service;
//
//import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
//import com.ssg.intern.dev.domain.comment.entity.Comment;
//import com.ssg.intern.dev.domain.comment.presentation.model.CommentSelectResponse;
//import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
//import com.ssg.intern.dev.domain.feed.entity.Feed;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//class CommentQueryServiceTest {
//
//    @Autowired
//    private CommentQueryService commentQueryService;
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private FeedRepository feedRepository;
//
//    @Test
//    @DisplayName("댓글 조회 테스트")
//    void getCommentsTest() {
//        // given
//        Feed feed = feedRepository.getById(1L);
//        commentRepository.save(
//                Comment.of(feed, "content 1", 2L)
//        );
//        commentRepository.save(
//                Comment.of(feed, "content 2", 3L)
//        );
//
//        //when
//        CommentSelectResponse response = commentQueryService.getComments(1L);
//
//        //then
//        assertThat(response.getCommentCount()).isEqualTo(2);
//        assertThat(response.getComments().size()).isEqualTo(2);
//    }
//}