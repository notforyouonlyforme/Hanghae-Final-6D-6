package com.example.sparta.hanghaefinal.controller;

import com.example.sparta.hanghaefinal.domain.Board;
import com.example.sparta.hanghaefinal.domain.Comment;
import com.example.sparta.hanghaefinal.dto.CommentFileDto;
import com.example.sparta.hanghaefinal.dto.CommentSaveRequestDto;
import com.example.sparta.hanghaefinal.repository.BoardRepository;
import com.example.sparta.hanghaefinal.repository.CommentRepository;
import com.example.sparta.hanghaefinal.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/board")
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final BoardRepository boardRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{boardId}/comment")
    public void saveComment(@PathVariable Long boardId, CommentFileDto fileDto) throws Exception {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        CommentSaveRequestDto commentSaveRequestDto = CommentSaveRequestDto.builder()
                .board(board)
                .contents(fileDto.getContents())
                .nickname(fileDto.getNickname())
                .build();
        commentService.saveComment(boardId, commentSaveRequestDto, fileDto.getImages());
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
