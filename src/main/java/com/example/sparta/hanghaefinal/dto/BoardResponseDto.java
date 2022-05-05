package com.example.sparta.hanghaefinal.dto;

import com.example.sparta.hanghaefinal.domain.Board;
import com.example.sparta.hanghaefinal.domain.Comment;
import com.example.sparta.hanghaefinal.domain.Image;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String nickname;
    //    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;
    private List<Image> images;
    private List<Comment> commentList;
    private int view;
    private boolean bookmark;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.nickname = entity.getNickname();
        this.images = entity.getImages();
        this.view = entity.getView();
        this.bookmark = entity.isBookmark();
        this.commentList = entity.getCommentList();
//        this.createdAt = entity.getCreatedAt();
//        this.modifiedAt = entity.getModifiedAt();
    }
}
