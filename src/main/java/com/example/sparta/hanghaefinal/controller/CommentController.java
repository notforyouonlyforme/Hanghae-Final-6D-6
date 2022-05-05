package com.example.sparta.hanghaefinal.controller;

import com.example.sparta.hanghaefinal.domain.Comment;
import com.example.sparta.hanghaefinal.repository.CommentRepository;
import com.example.sparta.hanghaefinal.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/board")
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/{boardId}/comment")
    public void saveComment(@PathVariable Long boardId, @RequestBody Comment comment) {
        commentService.saveComment(boardId, comment);
    }

    @PatchMapping("/{boardId}/comment/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        commentService.updateComment(commentId, comment);
    }
    @PostMapping("/{boardId}/comment/{commentId}/delete")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
