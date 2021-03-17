package com.meetcoder.web.feed.service;

import com.meetcoder.web.blog.domain.Feed;
import com.meetcoder.web.blog.domain.FeedRepository;
import com.meetcoder.web.blog.service.FeedApiService;
import com.meetcoder.web.blog.service.dto.FeedResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FeedApiServiceImplTest {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedApiService feedApiService;

    private static Stream<Arguments> PageRequestSource() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(0, 5),
                Arguments.of(1, 1),
                Arguments.of(1, 5)
        );
    }

    @AfterEach
    public void initialize() {
        feedRepository.deleteAll();
    }

    @DisplayName("Feed 목록 조회 & 페이징 처리")
    @ParameterizedTest
    @MethodSource("PageRequestSource")
    void getFeedsByPageable(final int page, final int size) {

        for (int i = 0; i < 25; i++) {
            //given
            final String title = "title" + i;
            final String description = "description" + i;
            final String link = "http://localhost";
            final Feed feed = Feed.builder()
                    .title(title)
                    .description(description)
                    .link(link)
                    .pubDate(Date.from(Instant.now()))
                    .build();

            //when
            feedRepository.save(feed);
        }

        final Pageable pageable = PageRequest.of(page, size);
        final Page<FeedResponse> list = feedApiService.getFeedsByPageable(pageable);

        //then
        assertThat(list.getTotalPages()).isEqualTo(25 / size);
        assertThat(list.getSize()).isEqualTo(size);
        assertThat(list.getContent().get(0).getTitle()).startsWith("title");
    }

}