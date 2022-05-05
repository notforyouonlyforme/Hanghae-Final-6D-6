package com.example.sparta.hanghaefinal.dto;

import com.example.sparta.hanghaefinal.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String contents;
    private String nickname;
    private int view;
    private boolean bookmark;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .contents(contents)
                .nickname(nickname)
                .view(view)
                .bookmark(isBookmark())
                .build();
    }

}
