package com.example.sparta.hanghaefinal.comment.controller;

import com.example.sparta.hanghaefinal.comment.dto.*;
import com.example.sparta.hanghaefinal.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/board")
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{boardId}/comment")
    public ResponseEntity<CommentSaveResponse> saveComment(@PathVariable Long boardId,
                                                           @ModelAttribute CommentSaveRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.saveComment(requestDto));
    }

    @PatchMapping("/{boardId}/comment")
    public ResponseEntity<CommentUpdateResponse> updateComment(@PathVariable Long boardId,
                                                               @ModelAttribute CommentUpdateRequestDto requestDto) {
        return ResponseEntity.ok(commentService.updateComment(requestDto));
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
