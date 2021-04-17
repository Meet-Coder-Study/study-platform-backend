package com.meetcoder.web.blog.controller;

import com.rometools.rome.io.FeedException;
import com.meetcoder.web.blog.service.BlogService;
import com.meetcoder.web.blog.service.dto.BlogUpdateRequest;
import com.meetcoder.web.blog.service.dto.BlogUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PutMapping("/{blogId}")
    public ResponseEntity<BlogUpdateResponse> updateBlog(@RequestBody final BlogUpdateRequest blogUpdateRequest, @PathVariable final Long blogId) throws IOException, FeedException {

        final BlogUpdateResponse blogUpdateResponse = blogService.blogUpdate(blogId, blogUpdateRequest);

        return ResponseEntity.ok(blogUpdateResponse);
    }

}
