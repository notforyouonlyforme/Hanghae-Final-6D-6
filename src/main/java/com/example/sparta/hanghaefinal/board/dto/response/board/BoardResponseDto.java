package com.example.sparta.hanghaefinal.board.dto.response.board;

import com.example.sparta.hanghaefinal.board.entity.Board;
import com.example.sparta.hanghaefinal.comment.entity.Comment;
import com.example.sparta.hanghaefinal.config.Timestamped;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class BoardResponseDto extends Timestamped {
    private Long id;
    private String title;
    private String contents;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private ArrayList<String> imagePath;
    private List<Comment> comments;
    private int view;
    private boolean bookmark;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.nickname = entity.getNickname();
        this.view = entity.getView();
        this.bookmark = entity.isBookmark();
        this.comments = entity.getCommentList();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
    }
}
