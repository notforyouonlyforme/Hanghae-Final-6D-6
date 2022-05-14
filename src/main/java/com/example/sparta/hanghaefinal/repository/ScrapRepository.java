package com.example.sparta.hanghaefinal.repository;

import com.example.sparta.hanghaefinal.domain.Hashtag;
import com.example.sparta.hanghaefinal.domain.PostToHashtag;
import com.example.sparta.hanghaefinal.domain.Posts;
import com.example.sparta.hanghaefinal.domain.ScrapId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<PostToHashtag, ScrapId> {
    List<PostToHashtag> findAllByPostId(Long postId);

    void deleteByHashtagAndPost(Posts post, Hashtag hashtag);
}
