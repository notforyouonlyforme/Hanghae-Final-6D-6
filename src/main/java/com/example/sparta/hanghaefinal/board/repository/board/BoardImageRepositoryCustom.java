package com.example.sparta.hanghaefinal.board.repository.board;

import com.example.sparta.hanghaefinal.board.entity.Image;

import java.util.List;

public interface BoardImageRepositoryCustom {

    List<Image> findBySavedImageUrl(Long boardId);
}
