package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.PostDto;
import com.masai.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;

	
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") Long id ){
        return ResponseEntity.ok(postService.getPostById(id));
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name="id") Long id){
        PostDto postResponse = postService.updatePost(postDto,id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }

}
