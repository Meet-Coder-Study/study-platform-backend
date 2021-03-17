package com.meetcoder.web.blog.service.dto;

import com.meetcoder.web.blog.domain.Blog;
import com.meetcoder.web.blog.domain.Feed;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class FeedResponse {
    private String title;

    private String link;

    private String description;

    private LocalDateTime pubDate;

    @Builder
    public FeedResponse(final String title, final String link, final String description, final LocalDateTime pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }

    public static FeedResponse of(final Feed feed) {
        return new FeedResponse(feed.getTitle(), feed.getLink(), feed.getDescription(), feed.getPubDate());
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
