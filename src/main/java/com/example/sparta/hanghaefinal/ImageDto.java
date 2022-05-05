package com.example.sparta.hanghaefinal;

import com.example.sparta.hanghaefinal.domain.Image;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDto {

    private String origFileName;

    private String filePath;

    private Long fileSize;

    @Builder
    public ImageDto (String origFileName, String filePath, Long fileSize) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

//    public Image toEntity() {
//        return Image.builder()
//                .origFileName(origFileName)
//                .filePath(filePath)
//                .fileSize(fileSize)
//                .build();
//    }
//
}
