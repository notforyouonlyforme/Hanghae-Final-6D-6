package com.example.sparta.hanghaefinal.comment.service;


import com.example.sparta.hanghaefinal.aws.S3Uploader;
import com.example.sparta.hanghaefinal.board.entity.Board;
import com.example.sparta.hanghaefinal.board.repository.board.BoardRepository;
import com.example.sparta.hanghaefinal.comment.dto.*;
import com.example.sparta.hanghaefinal.comment.entity.Comment;
import com.example.sparta.hanghaefinal.comment.entity.CommentImage;
import com.example.sparta.hanghaefinal.comment.repository.CommentImageRepository;
import com.example.sparta.hanghaefinal.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentImageRepository commentImageRepository;
    private final BoardRepository boardRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public CommentSaveResponse saveComment(CommentSaveRequestDto requestDto) {
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        Comment comment = commentRepository.save(Comment.createComment(requestDto.getContents(), requestDto.getNickname(), board));
        List<String> commentImages = uploadCommentImages(requestDto, comment);
        return new CommentSaveResponse(requestDto.getBoardId(), comment.getId(), commentImages);
    }

    private List<String> uploadCommentImages(CommentSaveRequestDto requestDto, Comment comment) {
        return requestDto.getImages().stream()
                .map(image -> s3Uploader.upload(image, "comment"))
                .map(commentUrl -> createCommentImage(comment, commentUrl))
                .map(commentImage -> commentImage.getImageUrl())
                .collect(Collectors.toList());
    }

    private CommentImage createCommentImage(Comment comment, String commentUrl) {
        return commentImageRepository.save(CommentImage.builder()
                .imageUrl(commentUrl)
                .storeFileName(StringUtils.getFilename(commentUrl))
                .comment(comment)
                .build());
    }

    @Transactional
    public CommentUpdateResponse updateComment(CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(requestDto.getCommentId()).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다.")
        );
        validateDeletedImages(requestDto);
        uploadCommentImages(requestDto, comment);
        List<String> saveImages = getSaveImages(requestDto);
        comment.updateComment(requestDto.getContents());
        return new CommentUpdateResponse(comment.getBoard().getId(), comment.getId(), saveImages);
    }

    private void validateDeletedImages(CommentUpdateRequestDto requestDto) {
        commentImageRepository.findBySavedImageUrl(requestDto.getCommentId()).stream()
                .filter(commentImage -> !requestDto.getSaveImageUrl().stream().anyMatch(Predicate.isEqual(commentImage.getImageUrl())))
                .forEach(url -> {
                    commentImageRepository.delete(url);
                    s3Uploader.deleteImage(url.getImageUrl());
                });
    }
    private void uploadCommentImages(CommentUpdateRequestDto request, Comment comment) {
        request.getImages()
                .stream()
                .forEach(file -> {
                    String url = s3Uploader.upload(file, "comment");
                    createCommentImage(comment, url);
                });
    }

    /**
     * PostImage 테이블에 저장 되어있는 이미지 경로를 추출
     */
    private List<String> getSaveImages(CommentUpdateRequestDto request) {
        return commentImageRepository.findBySavedImageUrl(request.getCommentId())
                .stream()
                .map(image -> image.getImageUrl())
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다.")
        );

        commentRepository.delete(comment);
    }

}
