package com.ssg.intern.dev.domain.mypage.service;


import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageCommandService {

    private final FeedRepository feedRepository;

    public void changeCommentBlocked(final long feedId) {

        final Feed feed = feedRepository.findById(feedId)
                                        .orElseThrow(EntityNotFoundException::new);

        feed.changeCommentStatus();
    }
}
