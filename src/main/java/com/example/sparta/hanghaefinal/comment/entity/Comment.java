package com.example.sparta.hanghaefinal.comment.entity;

import com.example.sparta.hanghaefinal.board.entity.Board;
import com.example.sparta.hanghaefinal.board.entity.Image;
import com.example.sparta.hanghaefinal.config.Timestamped;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String contents;
    private String nickname;

    @JsonBackReference
    @OneToMany(
            mappedBy = "comment",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Image> images = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(Board board, String contents, String nickname) {
        this.board = board;
        this.contents = contents;
        this.nickname = nickname;
    }

    public static Comment createComment(String contents, String nickname, Board board) {
        return Comment.builder()
                .contents(contents)
                .nickname(nickname)
                .board(board)
                .build();
    }

    public void updateComment(String contents) {
        this.contents = contents;
    }

    public void addImage(Image image) {
        this.images.add(image);
        if (image.getComment() != this) {
            image.setComment(this);
        }
    }


}
