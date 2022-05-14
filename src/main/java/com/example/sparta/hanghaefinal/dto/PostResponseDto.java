package com.example.sparta.hanghaefinal.dto;


import com.example.sparta.hanghaefinal.domain.Comments;
import com.example.sparta.hanghaefinal.domain.Posts;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class PostResponseDto {
    private String title;
    private String content;
    private String image;
    private String link;
    private String category;
    private Double latitude;
    private Double longitude;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<Comments> commentList;

    public PostResponseDto(Posts post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.image = post.getImage();
        this.link = post.getLink();
        this.category = post.getCategory();
        this.longitude = post.getLongitude();
        this.latitude = post.getLatitude();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.expiredAt = post.getExpiredAt();
        this.commentList = post.getCommentList();
    }

}
