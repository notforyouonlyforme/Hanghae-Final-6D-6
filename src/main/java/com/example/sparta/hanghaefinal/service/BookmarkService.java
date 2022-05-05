package com.example.sparta.hanghaefinal.service;

import com.example.sparta.hanghaefinal.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public void saveBookmark(Long boardId) {
        bookmarkRepository.addBookmark(boardId);

    }

    @Transactional
    public void removeBookmark(Long boardId) {
        bookmarkRepository.removeBookmark(boardId);
    }

}
