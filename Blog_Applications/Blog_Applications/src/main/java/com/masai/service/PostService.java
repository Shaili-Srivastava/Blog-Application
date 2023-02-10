package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.dto.PostDto;
import com.masai.dto.PostResponse;
import com.masai.model.Post;


@Service
public interface PostService {
	
	
	PostDto createPost(PostDto postDto);

//    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);
    
    List<Post> getAllPosts();

    PostDto updatePost(PostDto postDto, Long id );

    void deletePostById(Long id);

}
