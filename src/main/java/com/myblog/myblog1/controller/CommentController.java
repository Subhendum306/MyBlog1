package com.myblog.myblog1.controller;

import com.myblog.myblog1.payload.CommentDto;
import com.myblog.myblog1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {this.commentService = commentService;}
    //http://localhost:8080/api/comment?postId=1
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable long postId){
        CommentDto dto = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteComment(@PathVariable long id){ 
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }
    //http://localhost:8080/api/comment?1/post/1
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id,@RequestBody CommentDto commentDto,@PathVariable long postId){
        CommentDto dto=commentService.updateComment(id,commentDto,postId);
       return new ResponseEntity<> (dto,HttpStatus.OK);
    }

}
