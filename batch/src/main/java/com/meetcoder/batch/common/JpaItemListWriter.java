package com.meetcoder.batch.common;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JpaItemListWriter<T> extends JpaItemWriter<List<T>> {
    private final JpaItemWriter<T> jpaItemWriter;

    @Override
    public void write(final List<? extends List<T>> items) {
        final List<T> list = new ArrayList<>();

        for (final List<T> item : items) {
            list.addAll(item);
        }

        this.jpaItemWriter.write(list);
    }
}
