package com.example.sparta.hanghaefinal.dto;

import com.example.sparta.hanghaefinal.domain.Board;
import com.example.sparta.hanghaefinal.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    private String contents;
    private String nickname;
    private Board board;
    private List<MultipartFile> images;


    public Comment toEntity() {
        return Comment.builder()
                .board(board)
                .contents(contents)
                .nickname(nickname)
                .build();
    }
}
