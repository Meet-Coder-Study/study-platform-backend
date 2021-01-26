package com.study.platform.blog.domain;

import java.net.URL;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    private String title;

    private URL link;

    private String description;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    private LocalDateTime pubDate;

    @Builder
    public Feed(Long id, String title, URL link, String description, Blog blog, LocalDateTime pubDate) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.blog = blog;
        this.pubDate = pubDate;
    }
}

