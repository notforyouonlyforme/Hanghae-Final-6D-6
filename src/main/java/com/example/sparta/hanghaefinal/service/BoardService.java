package com.example.sparta.hanghaefinal.service;

import com.example.sparta.hanghaefinal.domain.Board;
import com.example.sparta.hanghaefinal.dto.BoardResponseDto;
import com.example.sparta.hanghaefinal.dto.BoardSaveRequestDto;
import com.example.sparta.hanghaefinal.dto.BoardUpdateRequestDto;
import com.example.sparta.hanghaefinal.repository.BoardRepository;
import com.example.sparta.hanghaefinal.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;


    @Transactional
    public BoardResponseDto saveBoard(BoardSaveRequestDto requestDto) {
        Board board = boardRepository.save(requestDto.toEntity());
        return new BoardResponseDto(board);

    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        board.update(requestDto.getTitle(), requestDto.getContents());
        return new BoardResponseDto(board);
    }

    public BoardResponseDto findBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        return new BoardResponseDto(board);
    }

    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }

    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        boardRepository.deleteById(id);

    }
}
