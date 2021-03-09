package com.study.platform.blog.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import com.study.platform.blog.service.dto.FeedResponse;

@Service
public interface FeedReader {
    List<FeedResponse> getFeeds(URL rssUrl) throws IOException, FeedException;

    FeedResponse getFeed(SyndEntry syndEntry) throws IOException, FeedException;
}
