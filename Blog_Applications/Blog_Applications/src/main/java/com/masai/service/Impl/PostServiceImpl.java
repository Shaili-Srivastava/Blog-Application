package com.masai.service.Impl;

import com.masai.dto.PostDto;
import com.masai.dto.PostResponse;
import com.masai.exception.ResourceNotFoundException;
import com.masai.model.Post;
import com.masai.repository.PostRepository;
import com.masai.service.PostService;

import java.awt.print.Pageable;
import java.util.List;

//import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	
	private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }
	 

	 

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		
		//Converter DTO to entity
        Post post = mapToPost(postDto);
        //Save new Post.
        Post newPost = postRepository.save(post);
        //Convert entity to DTO
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
		
	}

//	@Override
//	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
//		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
//        //create Pageable instance
//        Pageable pageable = PageRequest.of(pageNo,pageSize, sort );
//
//        Page<Post> posts = postRepository.findAll(pageable);
//
//        // get content for page object
//        List<Post> listOfPosts = posts.getContent();
//
//        //get to list and convert
//        List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
//
//        PostResponse postResponse = new PostResponse();
//        postResponse.setContent(content);
//        postResponse.setPageNo(posts.getNumber());
//        postResponse.setPageSize(posts.getSize());
//        postResponse.setTotalElement(posts.getTotalElements());
//        postResponse.setTotalPages(posts.getTotalPages());
//        postResponse.setLast(posts.isLast());
//        return postResponse;
//	}

	@Override
	public PostDto getPostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
	}

	

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        //Update data
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        //Save Update
        Post updatePost = postRepository.save(post);
        return mapToDto(updatePost);
	}

	@Override
	public void deletePostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
	}
	
	//Convert PostDTO to Entity
    private Post mapToPost(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
        return post;
    }
    
    //Convert Entity to PostDTO
    private PostDto mapToDto(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }




	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
		
	}

}
