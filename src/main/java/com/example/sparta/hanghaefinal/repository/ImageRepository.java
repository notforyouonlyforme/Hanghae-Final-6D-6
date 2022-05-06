package com.example.sparta.hanghaefinal.repository;

import com.example.sparta.hanghaefinal.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    //게시글 전체 이미지 가져오기
    List<Image> findAllByBoardId(Long boardId);
}
