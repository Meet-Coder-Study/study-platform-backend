package com.study.platform.blog.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.study.platform.blog.service.dto.FeedDto;

@Component
public class FeedReaderImpl implements FeedReader {

    @Override
    public List<FeedDto> getFeeds(URL rssUrl) throws IOException, FeedException {
        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed syndFeed = syndFeedInput.build(new XmlReader(rssUrl));

        List<SyndEntry> entries = syndFeed.getEntries();

        return entries.stream().map(this::getFeed).collect(Collectors.toList());
    }

    @Override
    public FeedDto getFeed(SyndEntry syndEntry) {
        try {
            return FeedDto.builder()
                    .title(syndEntry.getTitle())
                    .link(new URL(syndEntry.getLink()))
                    .description(syndEntry.getDescription().getValue())
                    .pubDate(syndEntry.getPublishedDate())
                    .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("test");
        }
    }

}
