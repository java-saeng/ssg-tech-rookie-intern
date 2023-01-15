package com.ssg.intern.dev.domain.bookmark.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.domain.bookmark.entity.Bookmark;
import com.ssg.intern.dev.domain.bookmark.entity.QBookmark;
import com.ssg.intern.dev.domain.feed.entity.QFeed;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.ssg.intern.dev.domain.bookmark.entity.QBookmark.*;
import static com.ssg.intern.dev.domain.feed.entity.QFeed.*;

@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Bookmark> findBookmarkByFeedAndAccount(final long feedId, final long accountId) {
        return Optional.ofNullable(queryFactory.selectFrom(bookmark)
                                               .innerJoin(feed).fetchJoin()
                                               .on(bookmark.feed.id.eq(feed.id))
                                               .where(eqAccountId(accountId).and(eqFeedId(feedId)))
                                               .fetchFirst());
    }

    private BooleanExpression eqFeedId(final long feedId) {
        return bookmark.feed.id.eq(feedId);
    }

    private BooleanExpression eqAccountId(final long accountId) {
        return bookmark.accountId.eq(accountId);
    }
}
