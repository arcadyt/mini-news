package com.arcady.mininews.dto;

import com.arcady.mininews.entities.Comment;
import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;

import java.time.ZonedDateTime;

@Data
public class CommentView {
    private Long id;
    private String text;
    private ZonedDateTime createdAt;

    public static Comment toEntity(CommentView view) {
        throw new NotImplementedException();
    }

    public static CommentView toView(Comment entity) {
        final CommentView commentView = new CommentView();
        commentView.setId(entity.getId());
        commentView.setText("VIEW: "+entity.getText());
        commentView.setCreatedAt(entity.getCreatedAt());
        return commentView;
    }
}
