package com.ssg.intern.dev.external.speical_review.adapter.out.persistence;

import com.ssg.intern.dev.external.speical_review.domain.SpecialReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialReviewRepository extends JpaRepository<SpecialReview, Long>, SpecialReviewRepositoryCustom {

    List<SpecialReview> findSpecialReviewByAccountId(long accountId);
}
