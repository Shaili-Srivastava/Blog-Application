package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	
}
