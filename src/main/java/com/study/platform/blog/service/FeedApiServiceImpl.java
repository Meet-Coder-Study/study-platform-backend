package com.study.platform.blog.service;

import com.study.platform.blog.domain.Feed;
import com.study.platform.blog.domain.FeedRepository;
import com.study.platform.blog.service.dto.FeedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedApiServiceImpl implements FeedApiService {

    private final FeedRepository feedRepository;

    @Override
    public Page<FeedDto> getFeedsByPageable(final Pageable pageable) {
        Page<Feed> feedList = feedRepository.findAll(pageable);
        return feedList.map(FeedDto::new);
    }

}
