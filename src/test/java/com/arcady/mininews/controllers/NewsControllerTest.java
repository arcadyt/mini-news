package com.arcady.mininews.controllers;

import com.arcady.mininews.dao.NewsItemRepository;
import com.arcady.mininews.dto.NewsItemRequest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.arcady.mininews.controllers.NewsController;
import com.arcady.mininews.dto.ListView;
import com.arcady.mininews.dto.NewsItemView;
import com.arcady.mininews.entities.NewsItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class) // This replaces @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Configure as needed
class NewsControllerTest {
    @Autowired
    NewsController newsController;

    @Autowired
    NewsItemRepository newsItemRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void getNewsOfToday() {
        final NewsItemRequest newsRequest1 = new NewsItemRequest();
        newsRequest1.setTitle("Borzoi");
        newsRequest1.setLink("www.borzoi.co.il");
        newsController.createNewsItem(newsRequest1);

        final NewsItemRequest newsRequest2 = new NewsItemRequest();
        newsRequest2.setTitle("AnotherDog");
        newsRequest2.setLink("www.anotherdog.co.il");
        final ResponseEntity<NewsItem> newsItem2Response = newsController.createNewsItem(newsRequest2);

        final NewsItemRequest newsRequest3 = new NewsItemRequest();
        newsRequest3.setTitle("YetAnotherDog");
        newsRequest3.setLink("www.yetanotherdog.co.il");
        final ResponseEntity<NewsItem> newsItem3Response = newsController.createNewsItem(newsRequest3);

        Long newsItem2Id = newsItem2Response.getBody().getId();
        Optional<NewsItem> newsItemOptional2 = newsItemRepository.findById(newsItem2Id);
        NewsItem newsItem2 = newsItemOptional2.get();
        newsItem2.setCreatedAt(ZonedDateTime.now(Clock.systemUTC()).withHour(0).withMinute(0).withSecond(0).withNano(0));
        newsItemRepository.saveAndFlush(newsItem2);

        Long newsItem3Id = newsItem3Response.getBody().getId();
        Optional<NewsItem> newsItemOptional3 = newsItemRepository.findById(newsItem3Id);
        NewsItem newsItem3 = newsItemOptional3.get();
        newsItem3.setCreatedAt(ZonedDateTime.now(Clock.systemUTC()).withHour(0).withMinute(0).withSecond(0).withNano(0).minusMinutes(1));
        newsItemRepository.saveAndFlush(newsItem3);

        ResponseEntity<ListView> actual = newsController.getNewsOfToday(0);
        Assert.assertEquals(2, actual.getBody().getContent().size());
    }
}