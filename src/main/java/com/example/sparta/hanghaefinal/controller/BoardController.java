package com.example.sparta.hanghaefinal.controller;

import com.example.sparta.hanghaefinal.domain.Image;
import com.example.sparta.hanghaefinal.dto.*;
import com.example.sparta.hanghaefinal.service.BoardService;
import com.example.sparta.hanghaefinal.service.BookmarkService;
import com.example.sparta.hanghaefinal.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final BookmarkService bookmarkService;
    private final ImageService imageService;

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

    //글, 파일 수정할 때
    @PatchMapping("/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, BoardFileDto boardFileDto) throws Exception {

        //제목, 내용 변경
        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
                .title(boardFileDto.getTitle())
                .contents(boardFileDto.getContents())
                .build();
        List<Image> dbImageList = imageService.findAllByBoard(id);
        List<MultipartFile> multipartFileList = boardFileDto.getImages();
        List<MultipartFile> updatedFileList = new ArrayList<>();
        if (CollectionUtils.isEmpty(dbImageList)) {
            if (!CollectionUtils.isEmpty(multipartFileList)) {
                for (MultipartFile multipartFile : multipartFileList) {
                    updatedFileList.add(multipartFile);
                }
            }
        } else {
            if (CollectionUtils.isEmpty(multipartFileList)) {
                for (Image dbImage : dbImageList) {
                    imageService.deleteImage(dbImage.getId());
                }
            } else {
                List<String> dbOriginNameList = new ArrayList<>();
                for (Image dbImage : dbImageList) {
                    ImageDto dbImageDto = imageService.findByImageId(dbImage.getId());
                    String dbOrigFileName = dbImageDto.getOrigFileName();
                    if (!multipartFileList.contains(dbOrigFileName)) {
                        imageService.deleteImage(dbImage.getId());
                    } else {
                        dbOriginNameList.add(dbOrigFileName);
                    }
                    for (MultipartFile multipartFIle : multipartFileList) {
                        String multipartOrigName = multipartFIle.getOriginalFilename();
                        if (!dbOriginNameList.contains(multipartOrigName)) {
                            updatedFileList.add(multipartFIle);
                        }
                    }
                }
            }
        }

        return boardService.updateBoard(id, requestDto, boardFileDto.getImages());
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
