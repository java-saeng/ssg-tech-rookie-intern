package com.ssg.intern.dev.domain.mypage.service;

import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse;
import com.ssg.intern.dev.domain.bookmark.dao.BookmarkRepository;
import com.ssg.intern.dev.domain.bookmark.entity.Bookmark;
import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.global.SortingCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;

import static com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MypageQueryServiceTest {

    @Autowired
    private MypageQueryService mypageQueryService;

    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private FeedRepository feedRepository;

    @BeforeEach
    void before() throws InterruptedException {
        //참기름을 둘러주세요
        bookmarkRepository.save(Bookmark.of(2L, feedRepository.findById(1L).orElseThrow(), true));
        Thread.sleep(1000);
        //달달 볶아주세요
        bookmarkRepository.save(Bookmark.of(2L, feedRepository.findById(2L).orElseThrow(), true));

        commentRepository.save(Comment.of(feedRepository.findById(1L).orElseThrow(),"댓글입니다" ,1L));
        commentRepository.save(Comment.of(feedRepository.findById(1L).orElseThrow(),"댓글입니다2" ,3L));
        commentRepository.save(Comment.of(feedRepository.findById(3L).orElseThrow(),"댓글입니다3" ,3L));
        commentRepository.save(Comment.of(feedRepository.findById(3L).orElseThrow(),"댓글입니다4" ,4L));
    }

    @Test
    @DisplayName("내 리뷰 전체 조회하기 테스트 - 최신순")
    void myReviewTest() {
        //given

        //when
        MyReviewProfileResponse feeds = mypageQueryService.getMyFeeds(2L, SortingCondition.NEWER);
        //then
        assertThat(feeds.getTotalReviewCount()).isEqualTo(4);
        assertThat(feeds.getReviews().get(0).getCommentCount()).isEqualTo(2);
        assertThat(feeds.getReviews().get(1).getCommentCount()).isEqualTo(0);
        assertThat(feeds.getReviews().get(2).getCommentCount()).isEqualTo(2);

    }

    @Test
    @DisplayName("북마크 썸네일 조회 테스트 - 최신순")
    @Transactional
    void newerTest() {
        //given
        //when
        BookmarkProfileResponse result = mypageQueryService.getThumbnails(2L, SortingCondition.NEWER);

        for(Thumbnail t : result.getThumbnails()) {
            System.out.println(t.getDescription());
        }

        //then
//        assertThat(result.getBookmarkTotalCount()).isEqualTo(2);
//        assertThat(result.getThumbnails().get(1).getDescription()).isEqualTo("참기름을 둘러주세요");
//        assertThat(result.getThumbnails().get(0).getDescription()).isEqualTo("달달 볶아주세요");
    }

    @Test
    @DisplayName("북마크 썸네일 조회 테스트 - 오래된 순")
    @Transactional
    void olderTest() {
        //given
        //when
        BookmarkProfileResponse result = mypageQueryService.getThumbnails(2L, SortingCondition.OLDER);

        for(Thumbnail t : result.getThumbnails()) {
            System.out.println(t.getDescription());
        }

//        //then
//        assertThat(result.getBookmarkTotalCount()).isEqualTo(2);
//        assertThat(result.getThumbnails().get(0).getDescription()).isEqualTo("참기름을 둘러주세요");
//        assertThat(result.getThumbnails().get(1).getDescription()).isEqualTo("달달 볶아주세요");
    }
}