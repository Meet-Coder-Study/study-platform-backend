package com.study.platform.blog.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlogDto {
    private final String title;
    private final String link;
}
