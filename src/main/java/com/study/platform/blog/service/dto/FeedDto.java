package com.study.platform.blog.service.dto;

import com.study.platform.blog.domain.Blog;
import com.study.platform.blog.domain.Feed;
import lombok.Builder;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDateTime;

@Getter
public class FeedDto {

    public FeedDto(Feed entity) {
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.link = entity.getLink();
        this.pubDate = entity.getPubDate();
    }

    private final String title;

    private final String link;

    private final String description;

    private final LocalDateTime pubDate;

    @Builder
    public FeedDto(final String title, final String link, final String description, final LocalDateTime pubDate) {
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
