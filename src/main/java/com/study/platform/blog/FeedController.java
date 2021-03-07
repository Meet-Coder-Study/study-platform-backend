package com.study.platform.blog;

import com.study.platform.blog.service.FeedApiService;
import com.study.platform.blog.service.dto.FeedDto;
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

    /**
     * @param pageable
     * req ex) GET /feeds?page=0&size=10
     * page default = 0
     * size default = 20
     */
    @GetMapping("/feeds")
    public ResponseEntity<Page<FeedDto>> getFeeds(final Pageable pageable) {
        final Page<FeedDto> feeds = feedApiService.getFeedsByPageable(pageable);
        return ResponseEntity.ok(feeds);
    }

}
