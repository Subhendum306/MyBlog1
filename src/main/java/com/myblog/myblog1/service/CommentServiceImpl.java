package com.myblog.myblog1.service;
import com.myblog.myblog1.entity.Comment;
import com.myblog.myblog1.entity.Post;
import com.myblog.myblog1.exception.ResourceNotFoundException;
import com.myblog.myblog1.payload.CommentDto;
import com.myblog.myblog1.repository.CommentRepository;
import com.myblog.myblog1.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CommentServiceImpl implements CommentService{
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    @Autowired
    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository=commentRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post=postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post Not Found with id:" +postId)
        );

      Comment comment =new Comment();
      comment.setEmail(commentDto.getEmail());
      comment.setText(commentDto.getText());
      comment.setPost(post);

      Comment savedComment =commentRepository.save(comment);

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
    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found for Id:" + postId)
        );
        Comment comment =commentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Comment not found for id:"+id)
        );
        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        Comment saveComment = commentRepository.save(c);

        return mapToDto(saveComment);
    }
     Comment mapToEntity(CommentDto commentDto){
         Comment comment = modelMapper.map(commentDto, Comment.class);
         return comment;
     }
     CommentDto mapToDto(Comment comment){
        CommentDto commentDto=modelMapper.map(comment,CommentDto.class);
        return commentDto;
     }


}
