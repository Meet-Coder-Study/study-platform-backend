package com.study.platform.blog.service;

import com.study.platform.blog.domain.Feed;
import com.study.platform.blog.domain.FeedRepository;
import com.study.platform.blog.service.dto.FeedDto;
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

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class FeedApiServiceImplTest {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedApiServiceImpl feedApiService;

    @AfterEach
    public void initialize() {
        feedRepository.deleteAll();
    }

    @DisplayName("Feed 목록 조회 & 페이징 처리")
    @ParameterizedTest
    @MethodSource("PageRequestSource")
    void getFeedsByPageable(int page, int size) {

        for (int i = 0; i < 25; i++) {
            //given
            String title = "title" + i;
            String description = "description" + i;
            String link = "http://localhost";
            Feed feed = Feed.builder()
                    .title(title)
                    .description(description)
                    .link(link)
                    .pubDate(LocalDateTime.now())
                    .build();

            //when
            feedRepository.save(feed);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<FeedDto> list = feedApiService.getFeedsByPageable(pageable);

        //then
        assertThat(list.getTotalPages()).isEqualTo(25 / size);
        assertThat(list.getSize()).isEqualTo(size);
        assertThat(list.getContent().get(0).getTitle()).startsWith("title");
    }

    private static Stream<Arguments> PageRequestSource() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(0, 5),
                Arguments.of(1, 1),
                Arguments.of(1, 5)
        );
    }


}