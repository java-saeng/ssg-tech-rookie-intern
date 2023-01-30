package com.ssg.intern.mock.domain.hashtag.dao;

import com.ssg.intern.mock.domain.hashtag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    List<HashTag> findDistinctTop10ByOrderByIdAsc();
}
