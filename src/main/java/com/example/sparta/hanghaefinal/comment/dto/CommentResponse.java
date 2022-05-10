package com.example.sparta.hanghaefinal.comment.dto;

import com.example.sparta.hanghaefinal.board.entity.Board;
import com.example.sparta.hanghaefinal.comment.entity.Comment;
import com.example.sparta.hanghaefinal.config.Timestamped;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse extends Timestamped {
    private Board board;
    private Long id;
    private String contents;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponse(Comment entity) {
        this.board = entity.getBoard();
        this.id = entity.getId();
        this.contents = entity.getContents();
        this.nickname = entity.getNickname();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
    }
}
