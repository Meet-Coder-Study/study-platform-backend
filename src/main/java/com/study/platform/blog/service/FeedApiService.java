package com.study.platform.blog.service;

import com.study.platform.blog.service.dto.FeedDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface FeedApiService {
    Page<FeedDto> getFeedsByPageable(Pageable pageable);
}
