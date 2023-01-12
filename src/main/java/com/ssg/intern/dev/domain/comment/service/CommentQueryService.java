package com.ssg.intern.dev.domain.comment.service;

import com.ssg.intern.dev.domain.comment.dao.CommentCustomRepository;
import com.ssg.intern.dev.domain.comment.dao.CommentCustomRepositoryImpl;
import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.comment.dao.CommentSingleDao;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentSelectResponse;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final FeedRepository feedRepository;
    private final CommentCustomRepositoryImpl commentCustomRepository;

    public CommentSelectResponse getComments(Long feedId) {
        Long commentCount = commentRepository.countByFeed(
                feedRepository.findById(feedId)
                        .orElseThrow(() -> new EntityNotFoundException("해당 피드가 존재하지 않습니다."))
        );
        List<CommentSingleDao> comments = commentCustomRepository.findAllInnerFetchJoinAccount();

        return new CommentSelectResponse(commentCount, comments);
    }

    public void checkCommentAccount(Long id, Long accountId) {
        if (!commentRepository.existsDistinctByIdAndAccountId(id, accountId)) {
            throw new EntityNotFoundException("해당 댓글이 존재하지 않거나 올바르지 않은 사용자입니다.");
        }
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 댓글이 존재하지 않습니다."));
    }
}
