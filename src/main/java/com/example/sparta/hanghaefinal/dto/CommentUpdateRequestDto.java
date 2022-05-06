package com.example.sparta.hanghaefinal.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentUpdateRequestDto {

    private String contents;

    @Builder
    public CommentUpdateRequestDto(String contents) {
        this.contents = contents;
    }
}
