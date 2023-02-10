package com.masai.service;

import java.util.List;



import com.masai.dto.CommentDto;


public interface CommentService {

	CommentDto createComment(Long postId,CommentDto commentDto);

    List<CommentDto> getCommentByPostId(Long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(Long postId,Long commentId, CommentDto commentDto);

    void deleteComment(Long postId, Long commentId);
}
