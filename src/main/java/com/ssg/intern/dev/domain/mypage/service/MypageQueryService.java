package com.ssg.intern.dev.domain.mypage.service;

import com.ssg.intern.dev.domain.bookmark.dao.BookmarkRepository;
import com.ssg.intern.dev.domain.mypage.presentation.model.BookmarkProfileResponse;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageQueryService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkProfileResponse getThumbnails(Long accountId, SortingCondition sortingCondition) {
        // 전체 북마크 개수
        //List - 리뷰 사진, 추천해요 개수, 리뷰 설명
        List<BookmarkProfileResponse.Thumbnail> thumbnails = bookmarkRepository.findThumbnails(accountId, sortingCondition);
        return BookmarkProfileResponse.builder()
                .bookmarkTotalCount(thumbnails.size())
                .thumbnails(thumbnails)
                .build();
    }
}
