package com.masai.controller;

import java.util.List;

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

import com.masai.dto.CommentDto;
import com.masai.service.CommentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/")
public class CommentController {
	
	private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
	
	
	@PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @PathVariable(value = "postId") Long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }
	
	@GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentByPostId(postId);
    }
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,@PathVariable(value = "commentId")Long commentId){
        CommentDto commentDto =  commentService.getCommentById(postId,commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
	
	 @PutMapping("/posts/{postId}/comments/{commentId}")
	    public ResponseEntity<CommentDto> updateComment(@Valid @PathVariable(value = "postId") Long postId,@PathVariable(value = "commentId") Long commentId,
	            @RequestBody CommentDto commentDto){

	        CommentDto updateComment = commentService.updateComment(postId, commentId, commentDto);
	        return new ResponseEntity<>(updateComment, HttpStatus.OK);
	 }
	 
	 @DeleteMapping("/posts/{postId}/comments/{commentId}")
	    public ResponseEntity<String> deleteComment(
	            @PathVariable(value = "postId") Long postId,
	            @PathVariable(value = "commentId") Long commentId){
	        commentService.deleteComment(postId, commentId);
	        return new ResponseEntity<>("Comment deleted sucessfuly", HttpStatus.OK);
	}
	


}
