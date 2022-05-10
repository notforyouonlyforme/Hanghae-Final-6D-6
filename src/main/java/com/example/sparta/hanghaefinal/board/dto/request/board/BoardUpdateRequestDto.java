package com.example.sparta.hanghaefinal.board.dto.request.board;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private Long boardId;
    private String title;
    private String contents;
    private List<String> saveImageUrl = new ArrayList<>();
    private List<MultipartFile> images = new ArrayList<>();
//    private LocalDateTime modifiedAt;

}
