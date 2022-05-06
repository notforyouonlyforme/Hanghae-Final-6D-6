package com.example.sparta.hanghaefinal.service;

import com.example.sparta.hanghaefinal.FileHandler;
import com.example.sparta.hanghaefinal.domain.Board;
import com.example.sparta.hanghaefinal.domain.Comment;
import com.example.sparta.hanghaefinal.domain.Image;
import com.example.sparta.hanghaefinal.dto.CommentSaveRequestDto;
import com.example.sparta.hanghaefinal.dto.CommentResponseDto;
import com.example.sparta.hanghaefinal.repository.BoardRepository;
import com.example.sparta.hanghaefinal.repository.CommentRepository;
import com.example.sparta.hanghaefinal.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final FileHandler fileHandler;

    @Transactional
    public CommentResponseDto saveComment(Long boardId, CommentSaveRequestDto requestDto, List<MultipartFile> images) throws Exception {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        Comment comment = commentRepository.save(requestDto.toEntity());
        List<Image> imageList = fileHandler.parseFileInfoComment(comment, images);
        if (!imageList.isEmpty()) {
            for (Image image : imageList) {
                comment.addImage(imageRepository.save(image));
            }
        }
        comment.saveComment(board);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
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
