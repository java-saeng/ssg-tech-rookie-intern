package com.ssg.intern.dev.domain.mypage.service;

import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse;
import com.ssg.intern.dev.global.SortingCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MypageQueryServiceTest {

    @Autowired
    private MypageQueryService mypageQueryService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FeedRepository feedRepository;

    @BeforeEach
    void before() {
        commentRepository.save(Comment.of(feedRepository.findById(1L).orElseThrow(),"댓글입니다" ,1L));
        commentRepository.save(Comment.of(feedRepository.findById(1L).orElseThrow(),"댓글입니다2" ,3L));
    }

    @Test
    @DisplayName("내 리뷰 전체 조회하기 테스트 - 최신순")
    void myReviewTest() {
        //given

        //when
        MyReviewProfileResponse feeds = mypageQueryService.getMyFeeds(2L, SortingCondition.NEWER);
        //then
        assertThat(feeds.getTotalReivewCount()).isEqualTo(4);
        assertThat(feeds.getReviews().get(0).getCommentCount()).isEqualTo(2);

    }

}