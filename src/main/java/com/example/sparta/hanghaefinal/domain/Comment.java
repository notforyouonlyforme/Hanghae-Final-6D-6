package com.example.sparta.hanghaefinal.domain;

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
    private Long id;

    private String contents;
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public void saveComment(Board board) {
        this.board = board;
    }

    public void updateComment(String contents) {
        this.contents = contents;
    }


}
