package com.example.sparta.hanghaefinal.repository;

import com.example.sparta.hanghaefinal.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {
    Optional<Hashtag> findByContent(String content);
}
