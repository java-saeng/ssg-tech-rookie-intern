package com.ssg.intern.dev.domain.mypage.presentation.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookmarkProfileResponse {
    private long bookmarkTotalCount;
    private List<Thumbnail> thumbnails;

    @Builder
    public BookmarkProfileResponse(final long bookmarkTotalCount, final List<Thumbnail> thumbnails) {
        this.bookmarkTotalCount = bookmarkTotalCount;
        this.thumbnails = thumbnails;
    }

    @Getter
    @NoArgsConstructor
    public static class Thumbnail {
        private String imageUrl;
        private long recommendCount;
        private String description;

        @Builder
        @QueryProjection
        public Thumbnail(final String imageUrl, final long recommendCount, final String description) {
            this.imageUrl = imageUrl;
            this.recommendCount = recommendCount;
            this.description = description;
        }
    }
}
