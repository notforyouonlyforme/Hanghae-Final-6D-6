package com.example.sparta.hanghaefinal.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@NoArgsConstructor
public class Hashtag {
    @Id
    @GeneratedValue
    private Long hashId;

    @Column(nullable = false, unique = true)
    private String content;

    @OneToMany(mappedBy = "hashtag")
    List<PostToHashtag> posts = new ArrayList<>();

    @Builder
    public Hashtag(String content){
        this.content = content;
    }
}
