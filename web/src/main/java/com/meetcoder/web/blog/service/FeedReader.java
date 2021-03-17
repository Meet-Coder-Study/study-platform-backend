package com.meetcoder.web.blog.service;

import com.meetcoder.web.blog.service.dto.BlogDto;
import com.meetcoder.web.blog.service.dto.FeedResponse;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface FeedReader {

    BlogDto getBlog(final String rssUrl) throws IOException, FeedException;

    List<FeedResponse> getFeeds(String rssUrl) throws IOException, FeedException;

    FeedResponse getFeed(SyndEntry syndEntry) throws IOException, FeedException;
}
