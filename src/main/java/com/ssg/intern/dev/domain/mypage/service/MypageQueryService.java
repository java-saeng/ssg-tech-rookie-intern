package com.ssg.intern.dev.domain.mypage.service;

import com.ssg.intern.dev.domain.feed.dao.FeedRepository;
import com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse;
import com.ssg.intern.dev.domain.bookmark.dao.BookmarkRepository;
import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageQueryService {

    private final FeedRepository feedRepository;
    private final BookmarkRepository bookmarkRepository;

    public MyReviewProfileResponse getMyFeeds(long accountId, SortingCondition sortingCondition) {
        List<MyReviewProfileResponse.MyReview> reviews = feedRepository.findMyReviews(accountId, sortingCondition);
        return new MyReviewProfileResponse(reviews.size(), reviews);

    }
    

    public BookmarkProfileResponse getThumbnails(Long accountId, SortingCondition sortingCondition) {
        List<BookmarkProfileResponse.Thumbnail> thumbnails = bookmarkRepository.findThumbnails(accountId, sortingCondition);
        return BookmarkProfileResponse.builder()
                .bookmarkTotalCount(thumbnails.size())
                .thumbnails(thumbnails)
                .build();
    }
}
