package com.study.platform.blog.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.study.platform.blog.service.dto.FeedResponse;
import com.study.platform.util.DateUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedReaderImpl implements FeedReader {

    @Override
    public List<FeedResponse> getFeeds(final URL rssUrl) throws IOException, FeedException {
        final SyndFeedInput syndFeedInput = new SyndFeedInput();
        final SyndFeed syndFeed = syndFeedInput.build(new XmlReader(rssUrl));

        final List<SyndEntry> feeds = syndFeed.getEntries();

        return feeds.stream()
                .map(this::getFeed)
                .collect(Collectors.toList());
    }

    @Override
    public FeedResponse getFeed(final SyndEntry syndEntry) {
        try {
            return FeedResponse.builder()
                    .title(syndEntry.getTitle())
                    .link(syndEntry.getLink())
                    .description(syndEntry.getDescription().getValue())
                    .pubDate(DateUtil.convertDateToLocalDateTime(syndEntry.getPublishedDate()))
                    .build();
        } catch (final Exception e) {
            throw new IllegalArgumentException("해당 feed를 찾을수 없습니다.");
        }
    }
}
