package com.ssg.intern.dev.domain.feed.dao;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.domain.feed.presentation.model.QMyReviewProfileResponse_MyReview;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.ssg.intern.dev.domain.comment.entity.QComment.*;
import static com.ssg.intern.dev.domain.feed.entity.QFeed.*;
import static com.ssg.intern.dev.domain.feed.presentation.model.MyReviewProfileResponse.*;
import static com.ssg.intern.mock.domain.review.entity.QSpecialReview.specialReview;

@RequiredArgsConstructor
public class FeedRepositoryCustomImpl implements FeedRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Feed> findAllFeeds(Pageable pageable) {
        final List<Feed> feeds = queryFactory.selectFrom(feed).distinct()
                                             .leftJoin(comment).fetchJoin()
                                             .on(feed.id.eq(comment.feed.id))
                                             .offset(pageable.getOffset())
                                             .limit(pageable.getPageSize())
                                             .orderBy(sort(pageable.getSort()))
                                             .fetch();

        return PageableExecutionUtils.getPage(feeds, pageable, feeds::size);
    }

    @Override
    public List<MyReview> findMyReviews(Long accountId, SortingCondition sortingCondition) {
        return queryFactory
                .select(new QMyReviewProfileResponse_MyReview(
                        specialReview.imageUrl,
                        specialReview.starScore,
                        specialReview.createdAt,
                        specialReview.description,
                        feed.recommendCount,
                        feed.bookmarkCount,
                        feed.comments.size(),
                        feed.isCommentBlocked
                )).distinct()
                .from(feed)
                .innerJoin(specialReview).fetchJoin()
                .on(feed.specialReviewId.eq(specialReview.id))
                .leftJoin(comment).fetchJoin()
                .on(comment.feed.id.eq(feed.id))
                .where(specialReview.account.id.eq(accountId))
                .orderBy(sorting(sortingCondition))
                .fetch()
                ;
    }

    private OrderSpecifier<?> sort(final Sort sort) {
        Order direction = Order.DESC;

        for (final Sort.Order order : sort) {
            if (order.getProperty().equals(SortingCondition.RECOMMENDATION.toString())) {
                return new OrderSpecifier<>(direction, feed.recommendCount);
            }
        }

        return new OrderSpecifier<>(direction, feed.createdAt);
    }

    private OrderSpecifier<?> sorting(final SortingCondition sortingCondition) {
        switch (sortingCondition) {
            case NEWER:
                return new OrderSpecifier<>(Order.DESC, specialReview.createdAt);
            case OLDER:
                return new OrderSpecifier<>(Order.ASC, specialReview.createdAt);
        }
        return null;
    }
}
