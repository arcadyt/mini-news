package com.arcady.mininews.controllers;

import com.arcady.mininews.dto.CommentRequest;
import com.arcady.mininews.dto.NewsItemRequest;
import com.arcady.mininews.entities.Comment;
import com.arcady.mininews.entities.NewsItem;
import com.arcady.mininews.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.ZonedDateTime;

@RestController
@RequestMapping(value = "/api/news", produces = "application/json")
public class NewsController {

    @Autowired
    private NewsService newsService;

    // --- public --- //
    @GetMapping("/today")
    public ResponseEntity<Page<NewsItem>> getNewsOfToday(
            @RequestParam(defaultValue = "0") int page
    ) {
        final ZonedDateTime start = ZonedDateTime.now().withHour(0).withMinute(0).withSecond(0);
        Page<NewsItem> newsPage = newsService.getNewsAfterDate(start, page);
        return ResponseEntity.ok(newsPage);
    }

    @GetMapping("/yesterday")
    public ResponseEntity<Page<NewsItem>> getNewsOfYesterday(
            @RequestParam(defaultValue = "0") int page
    ) {
        final ZonedDateTime end = ZonedDateTime.now(Clock.systemUTC()).withHour(0).withMinute(0).withSecond(0);
        final ZonedDateTime start = ZonedDateTime.now(Clock.systemUTC()).minusDays(1).withHour(0).withMinute(0).withSecond(0);
        Page<NewsItem> newsPage = newsService.getNewsBetweenDates(start, end, page);
        return ResponseEntity.ok(newsPage);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<NewsItem>> searchNews(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page
    ) {
        Page<NewsItem> searchResults = newsService.searchNewsByTitleOrderByVotesDesc(keyword, page);
        return ResponseEntity.ok(searchResults);
    }

    // --- private --- //
    @PostMapping(value = "/{newsId}/comments", consumes = "application/json")
    public ResponseEntity<Comment> addCommentToNewsItem(
            @PathVariable Long newsId,
            @RequestBody CommentRequest request
    ) {
        Comment addedComment = newsService.addCommentToNewsItem(newsId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedComment);
    }

    @PutMapping("/{newsId}/upvote")
    public ResponseEntity<Void> upvoteNewsItem(@PathVariable Long newsId) {
        newsService.upvoteNewsItem(newsId);
        return ResponseEntity.ok().build();
    }

    // --- admin --- //
    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<NewsItem> createNewsItem(@RequestBody NewsItemRequest request) {
        NewsItem createdNewsItem = newsService.createNewsItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNewsItem);
    }
}
