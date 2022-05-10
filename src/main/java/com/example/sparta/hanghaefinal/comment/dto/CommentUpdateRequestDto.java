package com.example.sparta.hanghaefinal.comment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentUpdateRequestDto {

    private Long commentId;
    private String contents;

    private List<String> saveImageUrl = new ArrayList<>();

    private List<MultipartFile> images = new ArrayList<>();

//    @Builder
//    public CommentUpdateRequestDto(String contents) {
//        this.contents = contents;
//    }
}
