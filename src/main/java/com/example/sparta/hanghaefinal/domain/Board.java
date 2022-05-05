package com.example.sparta.hanghaefinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String contents;
    private String nickname;
    private int view = 0;
    private boolean bookmark;

    @OneToMany(
            mappedBy = "board",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Image> images = new ArrayList<>();

    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board")
    private List<Bookmark> bookmarkList;

    @Builder
    public Board(String title, String contents, String nickname, int view, boolean bookmark) {
        this.title = title;
        this.contents = contents;
        this.nickname = nickname;
        this.view = view;
        this.bookmark = isBookmark();
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void addImage(Image image) {
        this.images.add(image);
        if (image.getBoard() != this) {
            image.setBoard(this);
        }
    }

}
