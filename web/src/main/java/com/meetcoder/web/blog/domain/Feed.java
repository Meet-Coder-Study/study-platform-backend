package com.meetcoder.web.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    private String title;

    private String link;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    private LocalDateTime pubDate;

    @Builder
    public Feed(final Long id, final String title, final String link, final String description, final Blog blog, final LocalDateTime pubDate) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.blog = blog;
        this.pubDate = pubDate;
    }
}

