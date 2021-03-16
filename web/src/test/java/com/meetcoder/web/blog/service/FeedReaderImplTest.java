package com.meetcoder.web.blog.service;

import com.meetcoder.web.blog.service.dto.FeedDto;
import com.rometools.rome.io.FeedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FeedReaderImplTest {

    private final FeedReader feedReader = new FeedReaderImpl();

    @DisplayName("BlogFeed 전체 가져오는 테스트")
    @Test
    void getFeeds() throws IOException, FeedException {
        final List<FeedDto> feeds = feedReader.getFeeds(new URL("https://rutgo-letsgo.tistory.com/rss"));

        assertAll(
                () -> assertThat(feeds).isNotEmpty()
        );
    }
}
