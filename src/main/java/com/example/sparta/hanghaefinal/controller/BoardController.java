package com.example.sparta.hanghaefinal.controller;

import com.example.sparta.hanghaefinal.dto.BoardResponseDto;
import com.example.sparta.hanghaefinal.dto.BoardSaveRequestDto;
import com.example.sparta.hanghaefinal.dto.BoardUpdateRequestDto;
import com.example.sparta.hanghaefinal.service.BoardService;
import com.example.sparta.hanghaefinal.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final BookmarkService bookmarkService;

    @PostMapping("/board")
    public BoardResponseDto saveBoard(@RequestBody BoardSaveRequestDto requestDto) {
        return boardService.saveBoard(requestDto);
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
