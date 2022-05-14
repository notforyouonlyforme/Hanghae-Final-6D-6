package com.example.sparta.hanghaefinal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@IdClass(ScrapId.class)
@NoArgsConstructor
public class PostToHashtag implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Posts post;

    @Id
    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    @JsonBackReference
    private Hashtag hashtag;

    @Builder
    public PostToHashtag(Posts post, Hashtag hashtag){
        this.post = post;
        this.hashtag = hashtag;
    }
}