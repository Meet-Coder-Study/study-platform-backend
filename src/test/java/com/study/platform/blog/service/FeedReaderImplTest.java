package com.study.platform.blog.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rometools.rome.io.FeedException;
import com.study.platform.blog.service.dto.FeedResponse;

class FeedReaderImplTest {

    private final FeedReader feedReader = new FeedReaderImpl();

    @DisplayName("BlogFeed 전체 가져오는 테스트")
    @Test
    void getFeeds() throws IOException, FeedException {
        List<FeedResponse> feeds = feedReader.getFeeds(new URL("https://rutgo-letsgo.tistory.com/rss"));

        assertAll(
                () -> assertThat(feeds).isNotEmpty()
        );
    }
}
