package com.study.platform.blog.service.dto;

import com.study.platform.blog.domain.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BlogUpdateResponse {
    private String blogLink;
    private String rssLink;
    private String title;

    public static BlogUpdateResponse of(final Blog blog) {
        return new BlogUpdateResponse(blog.getLink(), blog.getRssLink(), blog.getTitle());
    }
}
