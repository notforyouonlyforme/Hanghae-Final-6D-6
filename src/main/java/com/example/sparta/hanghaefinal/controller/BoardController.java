package com.example.sparta.hanghaefinal.controller;

import com.example.sparta.hanghaefinal.dto.BoardFileDto;
import com.example.sparta.hanghaefinal.dto.BoardResponseDto;
import com.example.sparta.hanghaefinal.dto.BoardSaveRequestDto;
import com.example.sparta.hanghaefinal.dto.BoardUpdateRequestDto;
import com.example.sparta.hanghaefinal.service.BoardService;
import com.example.sparta.hanghaefinal.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final BookmarkService bookmarkService;

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/board")
//    public BoardResponseDto saveBoard(
//            @RequestPart(value = "image", required = false) List<MultipartFile> images,
//            @RequestPart BoardSaveRequestDto requestDto) throws Exception {
//        return boardService.saveBoard(requestDto, images);
//    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/board")
    public BoardResponseDto saveBoard(
            BoardFileDto boardFileDto) throws Exception {
        BoardSaveRequestDto requestDto =
                BoardSaveRequestDto.builder()
                        .title(boardFileDto.getTitle())
                        .contents(boardFileDto.getContents())
                        .nickname(boardFileDto.getNickname())
                        .build();

        return boardService.saveBoard(requestDto, boardFileDto.getImages());
    }

    @PatchMapping("/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }

    @PostMapping("/board/{id}/delete")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto findBoard(@PathVariable Long id) {
        boardService.updateView(id);
        return boardService.findBoard(id);
    }

    @PostMapping("/board/{boardId}/bookmark")
    public void addBookmark(@PathVariable Long boardId) {
        bookmarkService.saveBookmark(boardId);
    }

    @PostMapping("/board/{boardId}/bookmark/delete")
    public void removeBookmark(@PathVariable Long boardId) {
        bookmarkService.removeBookmark(boardId);
    }
}
