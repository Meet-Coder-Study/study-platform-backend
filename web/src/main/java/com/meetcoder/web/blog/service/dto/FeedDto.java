package com.meetcoder.web.blog.service.dto;

import com.meetcoder.web.blog.domain.Blog;
import com.meetcoder.web.blog.domain.Feed;
import lombok.Builder;
import lombok.Getter;

import java.net.URL;
import java.util.Date;

@Getter
public class FeedDto {
    private final String title;

    private final URL link;

    private final String description;

    private final Date pubDate;

    @Builder
    public FeedDto(final String title, final URL link, final String description, final Date pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }

    public Feed toEntity(final Blog blog) {
        return Feed.builder()
                .blog(blog)
                .title(this.title)
                .link(this.link)
                .description(this.description)
                .pubDate(this.pubDate)
                .build();
    }
}
