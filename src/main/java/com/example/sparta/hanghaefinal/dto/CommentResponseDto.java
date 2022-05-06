package com.example.sparta.hanghaefinal.dto;

import com.example.sparta.hanghaefinal.domain.Board;
import com.example.sparta.hanghaefinal.domain.Comment;
import com.example.sparta.hanghaefinal.domain.Image;

import java.util.List;

public class CommentResponseDto {
    private Long id;
    private String contents;
    private String nickname;
    private List<Image> images;

    public CommentResponseDto(Comment entity) {
        this.id = entity.getId();
        this.contents = entity.getContents();
        this.nickname = entity.getNickname();
        this.images = entity.getImages();
    }
}
