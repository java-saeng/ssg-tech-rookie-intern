package com.ssg.intern.dev.domain.comment.service;

import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentRegisterRequest;
import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentCommandService {

    private final FeedRepository feedRepository;
    private final CommentRepository commentRepository;
    private final CommentQueryService commentQueryService;

    private static final int REPORT_LIMIT = 3;

    @Transactional
    public void createComment(Long feedId, Long accountId, CommentRegisterRequest request) {
        commentRepository.save(
                Comment.of(feedRepository.findById(feedId)
                                .orElseThrow(() -> new EntityNotFoundException("해당 피드가 존재하지 않습니다.")),
                        request.getContent(),
                        accountId)
        );
    }

    @Transactional
    public void updateComment(Long id, Long accountId, CommentRegisterRequest request) {
        commentQueryService.checkCommentAccount(id, accountId);
        commentQueryService.getCommentById(id)
                .updateContent(request.getContent());
    }

    @Transactional
    public void deleteComment(Long id, Long accountId) {
        commentQueryService.checkCommentAccount(id, accountId);
        commentRepository.deleteById(id);
    }

    @Transactional
    public void reportComment(Long id) {
        Comment comment = commentQueryService.getCommentById(id);
        if (comment.updateReportCount(1) >= REPORT_LIMIT) {
            commentRepository.deleteById(id);
        }
    }


}
