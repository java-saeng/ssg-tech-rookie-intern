package com.ssg.intern.dev.domain.comment.service;

import com.ssg.intern.dev.domain.comment.dao.CommentCustomRepositoryImpl;
import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import com.ssg.intern.dev.domain.comment.dao.CommentSingleDao;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import com.ssg.intern.dev.domain.comment.presentation.model.CommentSelectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final CommentCustomRepositoryImpl commentCustomRepositoryImpl;

    public CommentSelectResponse getComments(Long feedId) { //TODO: feed 존재 여부 확인이 필요할까?
        List<CommentSingleDao> comments = commentCustomRepositoryImpl.findAllInnerFetchJoinAccount(feedId);
        return CommentSelectResponse.builder()
                .commentCount((long) comments.size())
                .comments(comments)
                .build();
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
