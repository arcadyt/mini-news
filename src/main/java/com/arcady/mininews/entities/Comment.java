package com.arcady.mininews.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private ZonedDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "news_item_id")
    @JsonIgnore
    private NewsItem newsItem;
}