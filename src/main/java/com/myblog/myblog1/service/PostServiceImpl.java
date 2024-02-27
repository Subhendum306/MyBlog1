package com.myblog.myblog1.service;
import com.myblog.myblog1.entity.Post;
import com.myblog.myblog1.exception.ResourceNotFoundException;
import com.myblog.myblog1.payload.PostDto;
import com.myblog.myblog1.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
     private PostRepository postRepository;
     @Autowired
     private ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper){
        this.postRepository=postRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=mapToEntity(postDto);
        Post savePost = postRepository.save(post);
        //After Saveing the record again try to get the data from the database i.e from Entity class;
        PostDto dto=mapToDto(savePost);
       return dto;
    }
    @Override
    public PostDto getPostById(long id){
         Post post=postRepository.findById(id).orElseThrow(
                 ()->new ResourceNotFoundException("Post not found with id:"+id)
         );
         PostDto dto=mapToDto(post);
        return dto;
    }

    @Override
     public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort=(sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pagePost =  postRepository.findAll(pageable);
        List<Post> content = pagePost.getContent();
        List<PostDto> postDto = content.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
         return postDto;
    }
 PostDto mapToDto(Post post){
         PostDto dto=modelMapper.map(post,PostDto.class);
         return dto;
    }
 Post mapToEntity(PostDto postDto){
         Post post=modelMapper.map(postDto,Post.class);
         return post;
    }
}
