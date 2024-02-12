package com.myblog.myblog1.service;

import com.myblog.myblog1.entity.Post;
import com.myblog.myblog1.payload.PostDto;
import com.myblog.myblog1.repository.PostRepository;
import org.springframework.stereotype.Service;
@Service
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savePost=postRepository.save(post);

        PostDto dto =new PostDto();
        dto.setTitle(savePost.getTitle());
        dto.setTitle(savePost.getDescription());
        dto.setTitle(savePost.getContent());

        return dto;
    }
}
