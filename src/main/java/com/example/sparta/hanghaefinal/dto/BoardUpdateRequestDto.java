package com.example.sparta.hanghaefinal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private String title;
    private String contents;
//    private LocalDateTime modifiedAt;

    @Builder
    public BoardUpdateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
