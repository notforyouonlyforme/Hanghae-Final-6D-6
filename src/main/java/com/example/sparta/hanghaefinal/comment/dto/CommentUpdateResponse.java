package com.example.sparta.hanghaefinal.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateResponse {
    private Long boardId;
    private Long commentId;
    private List<String> saveImages = new ArrayList<>();
}
