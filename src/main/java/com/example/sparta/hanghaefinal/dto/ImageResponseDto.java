package com.example.sparta.hanghaefinal.dto;

import com.example.sparta.hanghaefinal.domain.Image;
import lombok.Data;

@Data
public class ImageResponseDto {
    private Long imageId;

    public ImageResponseDto(Image image) {
        this.imageId = image.getId();
    }
}
