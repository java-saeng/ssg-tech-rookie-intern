package com.ssg.intern.dev.domain.recommend.dao;

import com.ssg.intern.dev.domain.recommend.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository<Recommend, Long>, RecommendRepositoryCustom {

}
