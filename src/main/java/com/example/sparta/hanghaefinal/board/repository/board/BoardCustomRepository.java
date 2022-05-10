package com.example.sparta.hanghaefinal.board.repository.board;

import com.example.sparta.hanghaefinal.board.dto.response.board.BoardOneResponse;
import com.example.sparta.hanghaefinal.board.dto.response.board.BoardResponseDto;

import java.util.Optional;

public interface BoardCustomRepository {
    Optional<BoardOneResponse> findOneBoardById(Long boardId);

//    Page<RecentBoardResponse> findRecentBoards(Pageable pageable);

}
