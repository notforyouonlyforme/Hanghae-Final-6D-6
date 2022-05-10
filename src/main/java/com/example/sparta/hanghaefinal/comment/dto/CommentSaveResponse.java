package com.example.sparta.hanghaefinal.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveResponse {
    private Long boardId;
    private Long commentId;
    private List<String> imageUrl = new ArrayList<>();
}
