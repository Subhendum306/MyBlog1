package com.myblog.myblog1.service;

import com.myblog.myblog1.entity.Comment;
import com.myblog.myblog1.entity.Post;
import com.myblog.myblog1.exception.ResourceNotFoundException;
import com.myblog.myblog1.payload.CommentDto;
import com.myblog.myblog1.repository.CommentRepository;
import com.myblog.myblog1.repository.PostRepository;

public class CommentServiceImpl implements CommentService{
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post=postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post Not Found with id:" +postId)
        );

      Comment comment=new Comment();
      comment.setEmail(commentDto.getEmail());
      comment.setText(commentDto.getText());
      comment.setPost(post);

      Comment savedComment=commentRepository.save(comment);

      CommentDto dto=new CommentDto();
      dto.setId(savedComment.getId());
      dto.setEmail(savedComment.getEmail());
      dto.setText(savedComment.getText());
      return dto;
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }



}
