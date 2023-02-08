package com.ssg.intern.dev.external.hashtag.adapter.out;

import com.ssg.intern.dev.external.hashtag.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {

    List<HashTag> findDistinctTop10ByOrderByIdAsc();
}
