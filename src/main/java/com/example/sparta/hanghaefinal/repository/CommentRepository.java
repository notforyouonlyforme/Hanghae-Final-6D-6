package com.example.sparta.hanghaefinal.repository;

import com.example.sparta.hanghaefinal.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
