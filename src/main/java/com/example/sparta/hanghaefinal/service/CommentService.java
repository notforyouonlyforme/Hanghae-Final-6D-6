package com.example.sparta.hanghaefinal.service;

import com.example.sparta.hanghaefinal.domain.Board;
import com.example.sparta.hanghaefinal.domain.Comment;
import com.example.sparta.hanghaefinal.repository.BoardRepository;
import com.example.sparta.hanghaefinal.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void saveComment(Long boardId, Comment comment) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        comment.saveComment(board);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentId, Comment newComment) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다.")
        );
        comment.updateComment(newComment.getContents());
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
