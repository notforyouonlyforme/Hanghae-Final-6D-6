package com.example.sparta.hanghaefinal.repository;

import com.example.sparta.hanghaefinal.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
