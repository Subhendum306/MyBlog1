package com.myblog.myblog1.service;
import com.myblog.myblog1.entity.Post;
import com.myblog.myblog1.exception.ResourceNotFoundException;
import com.myblog.myblog1.payload.PostDto;
import com.myblog.myblog1.repository.PostRepository;
import org.springframework.stereotype.Service;
@Service
public class PostServiceImpl implements PostService{
     private PostRepository postRepository;
     public PostServiceImpl(PostRepository postRepository){
         this.postRepository = postRepository;
     }
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savePost = postRepository.save(post);
        //After Saveing the record again try to fetch the data from the database;
        PostDto dto=new PostDto();
        dto.setTitle(savePost.getTitle());
        dto.setDescription(savePost.getDescription());
        dto.setContent(savePost.getContent());
        return dto;
    }
    @Override
    public PostDto getPostById(long id){
         Post post=postRepository.findById(id).orElseThrow(
                 ()->new ResourceNotFoundException("Post not found with id:"+id)
         );
         PostDto dto=new PostDto();
         dto.setId(post.getId());
         dto.setTitle(post.getTitle());
         dto.setDescription(post.getDescription());
         dto.setContent(post.getContent());
        return dto;
    }
}

