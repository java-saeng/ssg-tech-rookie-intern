package com.ssg.intern.dev.domain.mypage.service;

import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageQueryService {

    private final FeedRepository feedRepository;

    public MyReviewProfileResponse getMyFeeds(long accountId, SortingCondition sortingCondition) {
        List<MyReviewProfileResponse.MyReview> reviews = feedRepository.findMyReviews(accountId, sortingCondition);
        return new MyReviewProfileResponse(reviews.size(), reviews);
    }
}
