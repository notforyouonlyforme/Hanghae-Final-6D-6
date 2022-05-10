package com.example.sparta.hanghaefinal.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentCustomRepository{

    private final JPAQueryFactory queryFactory;



}
