package com.ssg.intern.dev.bookmark.adapter.out.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.intern.dev.bookmark.domain.Bookmark;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.ssg.intern.dev.bookmark.domain.QBookmark.bookmark;
import static com.ssg.intern.dev.feed.domain.QFeed.feed;


@RequiredArgsConstructor
public class BookmarkRepositoryCustomImpl implements BookmarkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Bookmark> findBookmarkByFeedAndAccount(final long accountId, final long feedId) {
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
