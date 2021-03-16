package com.meetcoder.batch.job;

import com.meetcoder.web.blog.domain.Blog;
import com.meetcoder.web.blog.domain.BlogRepository;
import com.meetcoder.web.blog.domain.FeedRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestPropertySource(properties = {"job.name" + "feedJob"})
class FeedJobConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Test
    void blogFeedBatch() throws Exception {
        // given
        this.blogRepository.save(Blog.builder()
                .title("test")
                .link("https://rutgo-letsgo.tistory.com/rss")
                .build());

        final JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertAll(
                () -> assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED),
                () -> assertThat(feedRepository.findAll()).hasSize(50)
        );
    }
}