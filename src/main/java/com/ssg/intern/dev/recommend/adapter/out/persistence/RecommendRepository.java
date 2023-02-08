package com.ssg.intern.dev.recommend.adapter.out.persistence;

import com.ssg.intern.dev.recommend.domain.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendRepository extends JpaRepository<Recommend, Long>, RecommendRepositoryCustom {

}
