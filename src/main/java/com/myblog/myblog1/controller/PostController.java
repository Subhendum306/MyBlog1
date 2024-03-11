package com.myblog.myblog1.controller;

import com.myblog.myblog1.payload.PostDto;
import com.myblog.myblog1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController{
    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    //http://localhost:8080/api/post/create
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
      PostDto dto=postService.createPost(postDto);
      return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    //http://localhost:8080/api/post/perticular?id=1
    @GetMapping("/perticular")
    public ResponseEntity<PostDto> getPostById(@RequestParam long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/post?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping
     public List<PostDto> getAllPost(
             @RequestParam(name="pageNo",required=false,defaultValue="0")int pageNo,
             @RequestParam(name="pageSize",required=false,defaultValue="3")int pageSize,
             @RequestParam(name="sortBy",required=false,defaultValue="id")String sortBy,
             @RequestParam(name="sortDir",required=false,defaultValue="id")String sortDir){
        List<PostDto> postDto = postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
        return postDto;
    }
}


