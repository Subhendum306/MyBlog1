package com.myblog.myblog1.controller;

import com.myblog.myblog1.payload.PostDto;
import com.myblog.myblog1.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class PostController{
    private PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
      PostDto dto=postService.createPost(postDto);
      return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }


    //http://localhost:8080/api/posts?id=1
    @GetMapping
    public ResponseEntity<PostDto> getPostById(@RequestParam long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
