package com.ssg.intern.dev.domain.comment.dao;

import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentSelectResponse;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CommentCustomRepositoryImplTest {

    @Autowired
    private CommentCustomRepositoryImpl commentCustomRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Test
    @DisplayName("findAllInnerFetchJoinAccount() 메소드를 위한 쿼리 테스트")
    void test() throws InterruptedException {
        //given
        Feed feed = feedRepository.getById(1L);
        commentRepository.save(
                Comment.of(feed, "content 1", 2L)
        );
        Thread.sleep(1000);
        commentRepository.save(
                Comment.of(feed, "content 2", 3L)
        );

        //when
        List<CommentSingleDao> list = commentCustomRepository.findAllInnerFetchJoinAccount();

        //then
        assertThat(list.get(0).getEmail()).isEqualTo("dPqls@ssg.com");
        assertThat(list.get(0).getContent()).isEqualTo("content 2");

        assertThat(list.get(1).getEmail()).isEqualTo("wldnjs@ssg.com");
        assertThat(list.get(1).getContent()).isEqualTo("content 1");
    }
}