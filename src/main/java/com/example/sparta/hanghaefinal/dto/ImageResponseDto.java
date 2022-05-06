package com.example.sparta.hanghaefinal.dto;

import com.example.sparta.hanghaefinal.domain.Image;
import lombok.Data;

@Data
public class ImageResponseDto {
    private Long id;

    public ImageResponseDto (Image entity){
        this.id = entity.getId();
    }
}
