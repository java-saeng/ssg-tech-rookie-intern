package com.ssg.intern.dev.domain.comment.dao;

import com.ssg.intern.dev.domain.comment.entity.Comment;
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
    void test() {
        // select c.comment, a.email from comment as c join account as a
        // on c.account_id = a.id;

        //given
        Feed feed = Feed.from(1L);
        feedRepository.save(feed);
        Comment comm = Comment.of(feed, "comment content", 2L);
        commentRepository.save(comm);

        //when
//        List<CommentSingleDao> list = jpaQueryFactory
//                .select(new QCommentSingleDao(account.email, comment.content))
//                .from(comment)
//                .join(account)
//                .on(comment.accountId.eq(account.id))
//                .fetch();
        List<CommentSingleDao> list = commentCustomRepository.findAllInnerFetchJoinAccount();

        //then
        System.out.println(list.get(0));
        assertThat(list.get(0).getContent()).isEqualTo(comm.getContent());
        assertThat(list.get(0).getEmail()).isEqualTo("wldnjs@ssg.com");
    }
}