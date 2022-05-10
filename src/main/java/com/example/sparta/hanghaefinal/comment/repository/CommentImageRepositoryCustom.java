package com.example.sparta.hanghaefinal.comment.repository;

import com.example.sparta.hanghaefinal.board.entity.Image;
import com.example.sparta.hanghaefinal.comment.entity.CommentImage;

import java.util.List;

public interface CommentImageRepositoryCustom {
    List<CommentImage> findBySavedImageUrl(Long commentId);
}
