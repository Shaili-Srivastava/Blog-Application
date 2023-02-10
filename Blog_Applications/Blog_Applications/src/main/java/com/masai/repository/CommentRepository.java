package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByPostId(Long postId);

}
