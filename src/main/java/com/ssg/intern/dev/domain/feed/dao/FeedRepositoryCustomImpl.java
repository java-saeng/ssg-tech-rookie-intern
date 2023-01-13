package com.ssg.intern.dev.domain.feed.dao;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.feed.entity.Feed;
import com.ssg.intern.dev.global.SortingCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ssg.intern.dev.domain.comment.entity.QComment.*;
import static com.ssg.intern.dev.domain.feed.entity.QFeed.*;

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

    private OrderSpecifier<?> sort(final Sort sort) {
        Order direction = Order.DESC;

        for (final Sort.Order order : sort) {
            if (order.getProperty().equals(SortingCondition.RECOMMENDATION.toString())) {
                return new OrderSpecifier<>(direction, feed.recommendCount);
            }
        }

        return new OrderSpecifier<>(direction, feed.createdAt);
    }
}
