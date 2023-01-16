package com.ssg.intern.dev.domain.comment.dao;

import com.ssg.intern.dev.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
    Optional<Comment> findByIdAndAccountId(Long id, Long accountId);
}
