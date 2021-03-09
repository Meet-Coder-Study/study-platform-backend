package com.study.platform.blog;

import com.study.platform.blog.service.FeedApiService;
import com.study.platform.blog.service.dto.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedController {

    private final FeedApiService feedApiService;

    @GetMapping("/feeds")
    public ResponseEntity<Page<FeedResponse>> getFeeds(final Pageable pageable) {
        final Page<FeedResponse> feeds = feedApiService.getFeedsByPageable(pageable);
        return ResponseEntity.ok(feeds);
    }

}
