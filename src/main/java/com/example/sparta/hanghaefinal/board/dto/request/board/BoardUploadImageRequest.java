package com.example.sparta.hanghaefinal.board.dto.request.board;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardUploadImageRequest {
    private Long boardId;
    private List<MultipartFile> images = new ArrayList<>();
}
