package com.ssg.intern.dev.domain.comment.service;

import com.ssg.intern.dev.domain.comment.dao.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommnetQueryService {

    private final CommentRepository commentRepository;

    public void getComments(Long id) {
        // 전체 댓글 개수
        // List - 작성자 이메일, 댓글 내용
    }
}
