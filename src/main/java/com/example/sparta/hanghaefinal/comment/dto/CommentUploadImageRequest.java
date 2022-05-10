package com.example.sparta.hanghaefinal.comment.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class CommentUploadImageRequest {

    private Long boardId;
    private Long commentId;
    private List<MultipartFile> images = new ArrayList<>();
}
