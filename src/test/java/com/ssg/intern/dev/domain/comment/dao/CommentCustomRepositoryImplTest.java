package com.ssg.intern.dev.domain.comment.dao;

import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
    @DisplayName("피드 ID에 대한 댓글 조회 메서드 테스트")
    void test() throws InterruptedException {
        //given
        commentRepository.save(
                Comment.of(feedRepository.getById(1L), "content 1", 2L)
        );
        Thread.sleep(1000);
        commentRepository.save(
                Comment.of(feedRepository.getById(1L), "content 2", 3L)
        );
        commentRepository.save(
                Comment.of(feedRepository.getReferenceById(2L), "content 3", 4L)
        );

        //when
        List<CommentSingleDao> list = commentCustomRepository.findAllInnerFetchJoinAccount(1L);

        //then
        assertThat(list.size()).isEqualTo(2);

        assertThat(list.get(0).getEmail()).isEqualTo("dPqls@ssg.com");
        assertThat(list.get(0).getContent()).isEqualTo("content 2");

        assertThat(list.get(1).getEmail()).isEqualTo("wldnjs@ssg.com");
        assertThat(list.get(1).getContent()).isEqualTo("content 1");
    }
}