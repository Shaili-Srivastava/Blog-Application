package com.masai.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import com.masai.dto.CommentDto;
import com.masai.exception.BlogAPIException;
import com.masai.exception.ResourceNotFoundException;
import com.masai.model.Comment;
import com.masai.model.Post;
import com.masai.repository.CommentRepository;
import com.masai.repository.PostRepository;
import com.masai.service.CommentService;

public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		Comment comment = mapToEntity(commentDto);

        // retrive post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id", postId));

        // set post to comment entity
        comment.setPost(post);

        //save new comment
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
	}

	@Override
	public List<CommentDto> getCommentByPostId(Long postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map( comment -> mapToDto(comment) ).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		 // retrive post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id", postId));

        // retrive comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id", commentId));

        //validate if the comment belongs to the last post
        if ( !comment.getPost().getId().equals(post.getId()) ){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        return mapToDto(comment);
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id", postId));

        // retrive comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id", commentId));

        //validate if the comment belongs to the last post
        if ( !comment.getPost().getId().equals(post.getId()) ){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updateComment = commentRepository.save(comment);
        return mapToDto(updateComment);
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		// retrive post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id", postId));

        // retrive comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id", commentId));

        //validate if the comment belongs to the last post
        if ( !comment.getPost().getId().equals(post.getId()) ){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        commentRepository.delete(comment);
		
	}
	
	 private CommentDto mapToDto(Comment comment){
	      CommentDto commentDto = mapper.map(comment, CommentDto.class);
	      return commentDto;
	 }

	 private Comment mapToEntity(CommentDto commentDto){
	     Comment comment = mapper.map(commentDto, Comment.class);
	     return comment;
	 }

}
