package com.example.sparta.hanghaefinal.comment.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveRequestDto {
    private Long boardId;
    private Long commentId;
    private String contents;
    private String nickname;
    private List<MultipartFile> images = new ArrayList<>();

}
