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

    public void createComment(Long feedId, String accountId, CommentRegisterRequest request) {
        commentRepository.save(
                Comment.of(feedRepository.findById(feedId)
                                .orElseThrow(() -> new EntityNotFoundException("해당 피드가 존재하지 않습니다.")),
                        request.getContent(),
                        Long.parseLong(accountId))
        );
    }

    public void updateComment(Long id, String accountId, CommentRegisterRequest request) {
        Comment comment = commentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("해당 댓글이 존재하지 않습니다."));
        
        commentRepository.save(
                Comment.of(comment.getFeed(),
                        request.getContent(),
                        comment.getAccountId()
                ));
    }

    public void deleteComment(Long id, String accountId) {
        if(!commentRepository.existsDistinctByIdAndAccountId(id, Long.parseLong(accountId))) {
            throw new EntityNotFoundException("해당 댓글이 존재하지 않거나 올바르지 않은 사용자입니다.");
        }
        commentRepository.deleteById(id);
    }
}
