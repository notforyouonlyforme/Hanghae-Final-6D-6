package com.example.sparta.hanghaefinal.dto;

import com.example.sparta.hanghaefinal.domain.Board;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CommentFileDto {
    private Board board;
    private String contents;
    private String nickname;
    private List<MultipartFile> images;
}
