package com.example.sparta.hanghaefinal.controller;

import com.example.sparta.hanghaefinal.domain.Success;
import com.example.sparta.hanghaefinal.repository.HashtagRepository;
import com.example.sparta.hanghaefinal.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HashtagController {

    private final HashtagRepository hashtagRepository;
    private final HashtagService hashtagService;

    @PostMapping("/hashtag")
    public ResponseEntity<Success> addTag(@PathVariable("content") String content){
        hashtagService.saveTag(content);
        return new ResponseEntity<>(new Success(true, "태그 추가 성공"), HttpStatus.OK);
    }
}
