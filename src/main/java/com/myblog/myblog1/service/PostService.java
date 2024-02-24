package com.myblog.myblog1.service;

import com.myblog.myblog1.payload.PostDto;

import java.util.List;

public interface PostService {
   public PostDto createPost(PostDto postDto);
   public PostDto getPostById(long id);
    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
}
