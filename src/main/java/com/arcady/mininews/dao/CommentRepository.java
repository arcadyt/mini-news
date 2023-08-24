package com.arcady.mininews.dao;

import com.arcady.mininews.entities.Comment;
import com.arcady.mininews.entities.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByNewsItemOrderByCreatedAtDesc(NewsItem newsItem);
}