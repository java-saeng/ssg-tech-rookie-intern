package com.ssg.intern.dev.domain.recommend.dao;

import com.ssg.intern.dev.domain.recommend.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {

    @Override
    @Query("select r from Recommend r inner join fetch r.feed where r.id=:id")
    Optional<Recommend> findById(@Param("id") Long recommendId);
}
