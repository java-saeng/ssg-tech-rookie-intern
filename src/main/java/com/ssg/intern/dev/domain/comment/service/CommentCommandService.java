package com.ssg.intern.dev.domain.comment.service;

import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentRegisterRequest;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CommentCommandService {

    private final FeedRepository feedRepository;
    private final CommentRepository commentRepository;
    private final CommentQueryService commentQueryService;

    public void createComment(Long feedId, String accountId, CommentRegisterRequest request) {
        commentRepository.save(
                Comment.of(feedRepository.findById(feedId)
                                .orElseThrow(() -> new EntityNotFoundException("해당 피드가 존재하지 않습니다.")),
                        request.getContent(),
                        Long.parseLong(accountId))
        );
    }

    public void updateComment(Long id, String accountId, CommentRegisterRequest request) {
        commentQueryService.checkCommentAccount(id, Long.parseLong(accountId));
        Comment comment = commentQueryService.getCommentById(id);
        commentRepository.save(
                Comment.of(comment.getFeed(),
                        request.getContent(),
                        comment.getAccountId()
                ));
    }

    public void deleteComment(Long id, String accountId) {
        commentQueryService.checkCommentAccount(id, Long.parseLong(accountId));
        commentRepository.deleteById(id);
    }

    public void reportComment(Long id) {
        Comment comment = commentQueryService.getCommentById(id);
        if (comment.updateReportCount(1) >= 3) {
            commentRepository.deleteById(id);
        }
        commentRepository.save(comment);
    }


}
