package com.arcady.mininews.dto;

import com.arcady.mininews.entities.NewsItem;
import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NewsItemView {
    private Long id;
    private String title;
    private ZonedDateTime createdAt;
    private List<CommentView> comments;

    public static NewsItem toEntity(NewsItemView view) {
        throw new NotImplementedException();
    }

    public static NewsItemView toView(NewsItem newsItem) {
        final NewsItemView newsItemView = new NewsItemView();
        newsItemView.setId(newsItem.getId());
        newsItemView.setTitle("VIEW "+newsItem.getTitle());
        newsItemView.setCreatedAt(newsItem.getCreatedAt());
        List<CommentView> comments = newsItem.getComments().stream().map(CommentView::toView).collect(Collectors.toList());
        newsItemView.setComments(comments);
        return newsItemView;
    }
}
