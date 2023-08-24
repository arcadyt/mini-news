package com.arcady.mininews.dao;

import com.arcady.mininews.entities.NewsItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;

public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {

    @Modifying
    @Query(value = "UPDATE news_item n SET n.votes = n.votes + 1, n.version = n.version + 1 WHERE n.id = :newsId AND n.version = :currentVersion", nativeQuery = true)
    void upvoteNewsItem(@Param("newsId") Long newsId, @Param("currentVersion") Long currentVersion);

    @Query("SELECT n FROM NewsItem n " +
            "LEFT JOIN n.comments c " +
            "WHERE LOWER(n.title) LIKE %:keyword% " +
            "GROUP BY n.id " +
            "ORDER BY SUM(n.votes) + COUNT(c) DESC")
    Page<NewsItem> searchNewsByTitleOrderedBySumOfVotesAndCommentsDesc(
            @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT n FROM NewsItem n LEFT JOIN n.comments c WHERE n.createdAt >= :dateTime " +
            "GROUP BY n.id " +
            "ORDER BY SUM(n.votes) + COUNT(c) DESC")
    Page<NewsItem> findAllNewsAfterOrderedBySumOfVotesAndCommentsDesc(
            @Param("dateTime") ZonedDateTime start, Pageable pageable);

    @Query("SELECT n FROM NewsItem n LEFT JOIN n.comments c WHERE n.createdAt BETWEEN :start AND :end " +
            "GROUP BY n.id " +
            "ORDER BY SUM(n.votes) + COUNT(c) DESC")
    Page<NewsItem> findAllNewsBetweenOrderedBySumOfVotesAndCommentsDesc(
            @Param("start") ZonedDateTime start,
            @Param("end") ZonedDateTime end,
            Pageable pageable);
}