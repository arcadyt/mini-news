package com.arcady.mininews.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListView<T> {
    private List<T> content;
    private int pageSize;
    private int totalEntries;

    public static ListView toView(List list, int pageSize, int totalPages) {
        final ListView listView = new ListView();
        listView.setContent(list);
        listView.setPageSize(pageSize);
        listView.setTotalEntries(totalPages);
        return listView;
    }
}
