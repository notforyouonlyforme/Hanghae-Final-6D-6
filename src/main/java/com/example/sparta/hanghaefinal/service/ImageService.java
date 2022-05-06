package com.example.sparta.hanghaefinal.service;

import com.example.sparta.hanghaefinal.domain.Image;
import com.example.sparta.hanghaefinal.dto.ImageDto;
import com.example.sparta.hanghaefinal.dto.ImageResponseDto;
import com.example.sparta.hanghaefinal.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    //파일 개별 조회

    public ImageDto findByImageId(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 파일이 없습니다.")
        );
        ImageDto imageDto = ImageDto.builder()
                .origFileName(image.getOrigFileName())
                .filePath(image.getFilePath())
                .fileSize(image.getFileSize())
                .build();
        return imageDto;
    }

    //이미지 파일 전체 조회
    public List<ImageResponseDto> findAllByBoard(Long boardId) {
        List<Image> imageList = imageRepository.findAllByBoardId(boardId);

        return imageList.stream()
                .map(ImageResponseDto::new)
                .collect(Collectors.toList());
    }

}
