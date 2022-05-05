package com.example.sparta.hanghaefinal.service;

import com.example.sparta.hanghaefinal.FileHandler;
import com.example.sparta.hanghaefinal.domain.Board;
import com.example.sparta.hanghaefinal.domain.Image;
import com.example.sparta.hanghaefinal.dto.BoardResponseDto;
import com.example.sparta.hanghaefinal.dto.BoardSaveRequestDto;
import com.example.sparta.hanghaefinal.dto.BoardUpdateRequestDto;
import com.example.sparta.hanghaefinal.repository.BoardRepository;
import com.example.sparta.hanghaefinal.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final FileHandler fileHandler;


    @Transactional
    public BoardResponseDto saveBoard(BoardSaveRequestDto requestDto,
                                      List<MultipartFile>images) throws Exception {
        Board board = boardRepository.save(requestDto.toEntity());
        List<Image>imageList = fileHandler.parseFileInfo(images);

        if (!imageList.isEmpty()) {
            for (Image image : imageList) {
                board.addImage(imageRepository.save(image));
            }
        }
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
