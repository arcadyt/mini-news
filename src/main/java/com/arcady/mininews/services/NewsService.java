package com.arcady.mininews.services;

import com.arcady.mininews.dao.CommentRepository;
import com.arcady.mininews.dao.NewsItemRepository;
import com.arcady.mininews.dto.CommentRequest;
import com.arcady.mininews.dto.NewsItemRequest;
import com.arcady.mininews.entities.Comment;
import com.arcady.mininews.entities.NewsItem;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Value("${app.page.size:30}")
    private int pageSize;

    public List<NewsItem> getNewsByPage(int page) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
        Page<NewsItem> newsPage = newsItemRepository.findAll(pageable);
        return newsPage.getContent();
    }

    public NewsItem createNewsItem(NewsItemRequest request) {
        NewsItem newsItem = new NewsItem();
        newsItem.setTitle(request.getTitle());
        newsItem.setLink(request.getLink());
        newsItem.setCreatedAt(ZonedDateTime.now(Clock.systemUTC()).now());
        return newsItemRepository.save(newsItem);
    }

    public Comment addCommentToNewsItem(Long newsId, CommentRequest request) {
        NewsItem newsItem = newsItemRepository.findById(newsId)
                .orElseThrow(() -> new EntityNotFoundException("News item not found"));

        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setCreatedAt(ZonedDateTime.now());
        comment.setNewsItem(newsItem);

        return commentRepository.save(comment);
    }

    @Transactional
    public void upvoteNewsItem(Long newsId) {
        NewsItem newsItem = newsItemRepository.findById(newsId)
                .orElseThrow(() -> new EntityNotFoundException("News item not found"));
        newsItemRepository.upvoteNewsItem(newsId, newsItem.getVersion());
    }

    @Transactional(readOnly = true)
    public Page<NewsItem> getNewsAfterDate(ZonedDateTime start, int page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return newsItemRepository.findAllNewsAfterOrderedBySumOfVotesAndCommentsDesc(start, pageable);
    }

    @Transactional(readOnly = true)
    public Page<NewsItem> getNewsBetweenDates(ZonedDateTime start, ZonedDateTime end, int page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return newsItemRepository.findAllNewsBetweenOrderedBySumOfVotesAndCommentsDesc(start, end, pageable);
    }

    @Transactional(readOnly = true)
    public Page<NewsItem> searchNewsByTitleOrderByVotesDesc(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return newsItemRepository.searchNewsByTitleOrderedBySumOfVotesAndCommentsDesc(keyword, pageable);
    }
}
