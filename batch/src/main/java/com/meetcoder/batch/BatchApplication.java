package com.meetcoder.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class BatchApplication {
    public static void main(final String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
}
