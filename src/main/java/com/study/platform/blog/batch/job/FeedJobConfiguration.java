package com.study.platform.blog.batch.job;

import com.study.platform.blog.domain.Blog;
import com.study.platform.blog.domain.Feed;
import com.study.platform.blog.service.FeedReader;
import com.study.platform.blog.service.dto.FeedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class FeedJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final FeedReader feedReader;

    @Bean
    public Job feedJob() {
        return jobBuilderFactory.get("feedJob")
                .start(feedStop1())
                .build();
    }

    @Bean
    public Step feedStop1() {
        return this.stepBuilderFactory.get("feedStep1")
                .<Blog, List<Feed>>chunk(100)
                .reader(feedItemReader())
                .processor(feedItemProcessor())
                .writer(feedItemWriter())
                .build();
    }

    private JpaPagingItemReader<Blog> feedItemReader() {
        return new JpaPagingItemReaderBuilder<Blog>()
                .name("feedItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(100)
                .queryString("SELECT b FROM Blog b")
                .build();
    }

    private ItemProcessor<Blog, List<Feed>> feedItemProcessor() {
        return blog -> {
            final List<FeedDto> feeds = feedReader.getFeeds(blog.getLink());

            return feeds.stream()
                    .map(feedDto -> feedDto.toEntity(blog))
                    .collect(Collectors.toList());
        };
    }

    private JpaItemListWriter<Feed> feedItemWriter() {
        final JpaItemWriter<Feed> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);

        return new JpaItemListWriter<>(jpaItemWriter);
    }
}
