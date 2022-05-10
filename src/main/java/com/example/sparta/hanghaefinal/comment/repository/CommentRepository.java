package com.example.sparta.hanghaefinal.comment.repository;

import com.example.sparta.hanghaefinal.board.entity.Board;
import com.example.sparta.hanghaefinal.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
    Page<Comment> findByBoardOrderByIdDesc(Board board, Pageable pageable);
}
