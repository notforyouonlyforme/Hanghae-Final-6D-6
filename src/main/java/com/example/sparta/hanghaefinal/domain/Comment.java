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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn("parent_id")
//    private Comment parent;
//
//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
//    private List<Comment> children = new ArrayList<>();
}
