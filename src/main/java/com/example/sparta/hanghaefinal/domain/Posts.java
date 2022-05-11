package com.example.sparta.hanghaefinal.domain;

import com.example.sparta.hanghaefinal.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Posts extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column
    private String image;

    @Column
    private String link;

    @Column(nullable = false)
    private String category;

//    @ManyToMany(mappedBy = "post", cascade = ALL)
//    private List<Hashtag> hashtag = new ArrayList<>();

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
    private List<Comments> commentList = new ArrayList<>();

    public void addComment(Comments comment) {
        this.commentList.add(comment);
    }
//   태그 연결 기능 추가
//    public void addHashtag(Hashtag hashtag) {this.hashtag.add(hashtag);}


    @Builder
    public Posts(String title, String content, String image, String link, String category, Double longitude, Double latitude, LocalDateTime expiredAt){
        this.title = title;
        this.content = content;
        this.image = image;
        this.link = link;
        this.category = category;
        this.longitude = longitude;
        this.latitude = latitude;
        this.expiredAt = expiredAt;
    }

    public void update(PostRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.image = requestDto.getImage();
        this.category = requestDto.getCategory();
        this.expiredAt = LocalDateTime.parse(requestDto.getExpiredAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }

    public void update(String category) {
        this.category = category;
    }
}
