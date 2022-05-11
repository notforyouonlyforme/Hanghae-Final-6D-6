package com.example.sparta.hanghaefinal.repository;

import com.example.sparta.hanghaefinal.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Long> {

    Page<Posts> findAll(Pageable pageable);

//    위치 반경 내의 게시글 필터링
//    String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) *" +
//        " cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude))))";

//    @Query(value = "SELECT p FROM Posts p WHERE " + HAVERSINE_FORMULA + "< :distance AND p.expiredAt <=:now AND p.category NOT LIKE '%나눔완료%'")
    @Query(value = "SELECT p FROM Posts p WHERE p.expiredAt > :now AND p.category NOT LIKE '%나눔완료%'")
    List<Posts> findPostsToUser(@Param("now") LocalDateTime now);

    List<Posts> findAllByExpiredAtBefore(LocalDateTime now);

    void deleteByPostId(Long postId);

    Optional<Posts> findByPostId(Long postId);
}