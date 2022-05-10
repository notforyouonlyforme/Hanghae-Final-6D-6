package com.example.sparta.hanghaefinal.comment.repository;

import com.example.sparta.hanghaefinal.comment.entity.CommentImage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.sparta.hanghaefinal.comment.entity.QComment.comment;
import static com.example.sparta.hanghaefinal.comment.entity.QCommentImage.commentImage;


@RequiredArgsConstructor
public class CommentImageRepositoryImpl implements CommentImageRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentImage> findBySavedImageUrl(Long commentId) {
        return queryFactory
                .selectFrom(commentImage)
                .innerJoin(commentImage.comment, comment)
                .where(comment.id.eq(commentId))
                .fetch();
    }
}
