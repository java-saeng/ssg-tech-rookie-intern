package com.ssg.intern.dev.domain.comment.entity;

import com.ssg.intern.common.BaseEntity;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @Column(nullable = false)
    private String content;

    private int reportCount;

    public Comment(final Feed feed, final String content) {
        this.feed = feed;
        this.content = content;
        this.reportCount = 0;
    }

    public static Comment of(final Feed feed, final String content) {
        return new Comment(feed, content);
    }
}
