package com.example.sparta.hanghaefinal.service;

import com.example.sparta.hanghaefinal.advice.RestException;
import com.example.sparta.hanghaefinal.domain.Hashtag;
import com.example.sparta.hanghaefinal.domain.PostToHashtag;
import com.example.sparta.hanghaefinal.domain.Posts;
import com.example.sparta.hanghaefinal.domain.Success;
import com.example.sparta.hanghaefinal.repository.HashtagRepository;
import com.example.sparta.hanghaefinal.repository.PostRepository;
import com.example.sparta.hanghaefinal.repository.ScrapRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;


    @ApiOperation(value = "스크랩 목록")
    public void scrapList(@PathVariable("postId") Long postId) {
        List<PostToHashtag> scrapList = scrapRepository.findAllByPostId(postId);

        List<Hashtag> tagList = new ArrayList<>();
        for (PostToHashtag scrap :scrapList)
            tagList.add(scrap.getHashtag());
    }

    @ApiOperation(value = "스크랩 추가")
    public void ScrapAdd(@RequestParam Long postId, @RequestParam String tag) {
        Posts post = postRepository.findByPostId(postId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 postId가 존재하지 않습니다.")
        );
        Hashtag hashtag = hashtagRepository.findByContent(tag).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 hashtag가 존재하지 않습니다.")
        );

        PostToHashtag scrap = PostToHashtag.builder()
                .post(post)
                .hashtag(hashtag)
                .build();

        scrapRepository.save(scrap);
    }

    @ApiOperation(value = "연관성 제거")
    public void scrapDelete(@RequestParam Long postId, @RequestParam String tag) {
        Posts post = postRepository.findByPostId(postId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 postId가 존재하지 않습니다.")
        );
        Hashtag hashtag = hashtagRepository.findByContent(tag).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 hashtag가 존재하지 않습니다.")
        );

        scrapRepository.deleteByHashtagAndPost(post, hashtag);
    }

}
