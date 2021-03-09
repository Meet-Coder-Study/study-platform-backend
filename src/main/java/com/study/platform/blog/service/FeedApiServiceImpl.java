package com.study.platform.blog.service;

import com.study.platform.blog.domain.Feed;
import com.study.platform.blog.domain.FeedRepository;
import com.study.platform.blog.service.dto.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedApiServiceImpl implements FeedApiService {

    private final FeedRepository feedRepository;

    @Override
    public Page<FeedResponse> getFeedsByPageable(final Pageable pageable) {
        Page<Feed> feedList = feedRepository.findAll(pageable);
        return feedList.map(FeedResponse::new);
    }

}
