package com.example.sparta.hanghaefinal.controller;

import com.example.sparta.hanghaefinal.advice.RestException;
import com.example.sparta.hanghaefinal.domain.Success;
import com.example.sparta.hanghaefinal.dto.PostRequestDto;
import com.example.sparta.hanghaefinal.dto.PostResponseDto;
import com.example.sparta.hanghaefinal.dto.PostUpdateDto;
import com.example.sparta.hanghaefinal.repository.PostRepository;
import com.example.sparta.hanghaefinal.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    //게시글 목록 불러오기(유저 기준으로 반경 5km 이내의 게시글 필터링) 미구현
    @GetMapping("/api/post")
    public List<PostResponseDto> findPostAll(HttpServletRequest request) {
        int pagingCnt;
        if (request.getHeader("PAGING_CNT") == null) {
            pagingCnt = 0;
        } else {
            pagingCnt = Integer.parseInt(request.getHeader("PAGING_CNT"));
        }

        return postService.findPosts(pagingCnt);
    }

    @GetMapping("/api/post/{postId}")
    public PostResponseDto findPost(@PathVariable Long postId) {
//        처리방법 생각해보기 (이미 로그인 없으면 못들어오는데 한 번 더 확인을 해야하는가?)
        return postService.findOne(postId);
    }

    @PostMapping("/api/post")
    public ResponseEntity<Success> savePost(@RequestBody @Valid PostRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                throw new RestException(HttpStatus.BAD_REQUEST, "잘못된 입력방법입니다.");
            }
        }
        postService.save(requestDto);
        return new ResponseEntity<>(new Success(true, "게시글 저장 성공"), HttpStatus.OK);
    }

    @DeleteMapping("/api/post/{postId}")
    public ResponseEntity<Success> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return new ResponseEntity<>(new Success(true, "게시글 삭제 성공"), HttpStatus.OK);
    }

    @PutMapping("/api/post/{postId}")
    public ResponseEntity<Success> modifyPost(@PathVariable Long postId, @RequestBody @Valid PostRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                throw new RestException(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
            }
        }
        postService.modify(postId, requestDto);
        return new ResponseEntity<>(new Success(true, "게시글 수정 성공"), HttpStatus.OK);
    }


    @PatchMapping("/api/post/{postId}")
    public ResponseEntity<Success> completedPost(@PathVariable("postId") Long postId, @RequestBody PostUpdateDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                throw new RestException(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
            }
        }
        postService.modify(postId, requestDto);
        return new ResponseEntity<>(new Success(true, "나눔 완료"), HttpStatus.OK);
    }
}