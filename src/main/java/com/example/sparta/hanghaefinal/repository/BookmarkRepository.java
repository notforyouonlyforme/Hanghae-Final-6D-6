package com.example.sparta.hanghaefinal.repository;

import com.example.sparta.hanghaefinal.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Modifying
    @Query(value = "INSERT INTO bookmark(board_id) VALUES(:boardId)", nativeQuery = true)
    void addBookmark(Long boardId);

    @Modifying
    @Query(value = "DELETE FROM bookmark where board_id = :boardId", nativeQuery = true)
    void removeBookmark(Long boardId);
}
