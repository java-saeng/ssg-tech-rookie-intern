package com.ssg.intern.dev.bookmark.adapter.out.persistence;

import com.ssg.intern.dev.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkRepositoryCustom {

    List<Bookmark> findBookmarksByAccountId(long accountId);
}