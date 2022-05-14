package com.example.sparta.hanghaefinal.service;

import com.example.sparta.hanghaefinal.domain.Hashtag;
import com.example.sparta.hanghaefinal.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public void saveTag(String content){

        Hashtag hashtag = Hashtag.builder()
                .content(content)
                .build();

        hashtagRepository.save(hashtag);
    }
}
