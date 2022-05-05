package com.example.sparta.hanghaefinal.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardFileDto {
    private String title;
    private String contents;
    private String nickname;
    private List<MultipartFile> images;
}
