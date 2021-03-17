package com.meetcoder.web.blog.service;

import com.meetcoder.web.blog.domain.Blog;
import com.meetcoder.web.blog.domain.BlogRepository;
import com.meetcoder.web.blog.service.dto.BlogDto;
import com.meetcoder.web.blog.service.dto.BlogUpdateRequest;
import com.meetcoder.web.blog.service.dto.BlogUpdateResponse;
import com.rometools.rome.io.FeedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Transactional
@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final FeedReader feedReader;

    public BlogUpdateResponse blogUpdate(final Long blogId, final BlogUpdateRequest blogUpdateRequest) throws IOException, FeedException {
        final Blog findBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("해당 블로그를 찾을 수 없습니다." + blogId));
        final BlogDto blogDto = feedReader.getBlog(blogUpdateRequest.getRssLink());

        findBlog.updateLink(blogDto.getLink(), blogDto.getTitle(), blogUpdateRequest.getRssLink());

        return BlogUpdateResponse.of(findBlog);
    }
}
