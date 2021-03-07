package com.study.platform.blog.service;

import com.rometools.rome.io.FeedException;
import com.study.platform.blog.domain.Blog;
import com.study.platform.blog.domain.BlogRepository;
import com.study.platform.blog.service.dto.BlogDto;
import com.study.platform.blog.service.dto.BlogUpdateRequest;
import com.study.platform.blog.service.dto.BlogUpdateResponse;
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
