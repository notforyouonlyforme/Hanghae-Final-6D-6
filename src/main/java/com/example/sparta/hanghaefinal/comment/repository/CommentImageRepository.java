package com.example.sparta.hanghaefinal.comment.repository;

import com.example.sparta.hanghaefinal.board.entity.Image;
import com.example.sparta.hanghaefinal.comment.entity.CommentImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentImageRepository extends JpaRepository<CommentImage, Long>, CommentImageRepositoryCustom{
}
