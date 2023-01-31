//package com.ssg.intern.dev.domain.comment.dao;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.ssg.intern.dev.domain.comment.entity.Comment;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static com.ssg.intern.dev.domain.comment.entity.QComment.comment;
//import static com.ssg.intern.dev.domain.feed.entity.QFeed.feed;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@DisplayName("CommentRepository Querydsl Integration Test")
//class CommentRepositoryImplTest {
//
//    @Autowired
//    JPAQueryFactory queryFactory;
//
//    @Test
//    @DisplayName("testFindCommentsByFeedId() : 댓글 조회 시 feed를 inner fetch join 할 수 있다.")
//    void testFindCommentsByFeedId() {
//        //when
//        final List<Comment> result = queryFactory.selectFrom(comment)
//                                                 .innerJoin(feed).fetchJoin()
//                                                 .on(comment.feed.id.eq(1L))
//                                                 .fetch();
//
//        //then
//        assertEquals(result.size(), 0);
//    }
//}