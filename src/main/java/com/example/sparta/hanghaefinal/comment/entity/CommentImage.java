package com.example.sparta.hanghaefinal.comment.entity;

import com.example.sparta.hanghaefinal.board.entity.Board;
import com.example.sparta.hanghaefinal.config.Timestamped;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CommentImage extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_image_id")
    private Long id;

//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "board_id")
//    private Board board;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private String imageUrl;
    private String storeFileName;
    private String image;

//    private String origFileName;
//    private String filePath;
//    private Long fileSize;

    //    @Builder
//    public Image(String origFileName, String filePath, Long fileSize) {
//        this.origFileName = origFileName;
//        this.filePath = filePath;
//        this.fileSize = fileSize;
//    }

    @Builder
    public CommentImage(String imageUrl, String storeFileName, Comment comment) {
        this.imageUrl = imageUrl;
        this.storeFileName = storeFileName;
        this.comment = comment;
    }

    public CommentImage(Comment comment, String image) {
        this.comment = comment;
        this.image = image;
    }

//    private String image;
//
//    public void setBoard(Board board) {
//        this.board = board;
//        if (!board.getImages().contains(this)) {
//            board.getImages().add(this);
//        }
//    }
//
//    public void setComment(Comment comment) {
//        this.comment = comment;
//        if (!comment.getImages().contains(this)) {
//            comment.getImages().add(this);
//        }
//    }

}
