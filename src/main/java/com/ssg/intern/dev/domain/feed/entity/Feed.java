package com.ssg.intern.dev.domain.feed.entity;

import com.ssg.intern.common.BaseEntity;
import com.ssg.intern.dev.domain.comment.entity.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Feed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long specialReviewId;

    @Column(nullable = false)
    private long bookmarkCount;

    @Column(nullable = false)
    private long recommendCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "feed")
    private List<Comment> comments = new ArrayList<>();

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isCommentBlocked;

    private Feed(final Long specialReviewId) {
        this.specialReviewId = specialReviewId;
    }

    public static Feed from(Long specialReviewId) {
        return new Feed(specialReviewId);
    }

    public void changeCommentStatus() {
        isCommentBlocked = !isCommentBlocked;
    }

    public void increaseRecommend() {
        recommendCount++;
    }

    public void decreaseRecommend() {
        recommendCount--;
    }

    public void increaseBookmark() {
        bookmarkCount++;
    }
}
