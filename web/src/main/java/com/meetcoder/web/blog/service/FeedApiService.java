package com.meetcoder.web.blog.service;

import com.meetcoder.web.blog.domain.Feed;
import com.meetcoder.web.blog.domain.FeedRepository;
import com.meetcoder.web.blog.service.dto.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedApiService {

    private final FeedRepository feedRepository;

    public Page<FeedResponse> getFeedsByPageable(final Pageable pageable) {
        final Page<Feed> feedList = feedRepository.findAll(pageable);
        return feedList.map(FeedResponse::of);
    }
}
