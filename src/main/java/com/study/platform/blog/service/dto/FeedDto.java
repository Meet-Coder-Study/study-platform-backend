package com.study.platform.blog.service.dto;

import java.net.URL;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedDto {
    private final String title;

    private final URL link;

    private final String description;

    private final Date pubDate;

    @Builder
    public FeedDto(String title, URL link, String description, Date pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }
}
