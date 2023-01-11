package com.ssg.intern.mock.domain.review.dao;

import com.ssg.intern.mock.domain.review.entity.SpecialReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialReviewRepository extends JpaRepository<SpecialReview, Long> {
}
