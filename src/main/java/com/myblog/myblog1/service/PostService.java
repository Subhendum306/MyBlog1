package com.myblog.myblog1.service;

import com.myblog.myblog1.payload.PostDto;

public interface PostService {
   public PostDto createPost(PostDto postDto);
   public PostDto getPostById(long id);
}
