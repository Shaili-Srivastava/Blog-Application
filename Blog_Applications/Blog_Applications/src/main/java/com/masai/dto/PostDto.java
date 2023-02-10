package com.masai.dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class PostDto {

	private Long id;
	
	@NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characteres")
    private String title;
	
	 @NotEmpty
	 @Size(min = 2, message = "Post description should have at least 10 characteres")
	 private String description;
	 
	 @NotEmpty
	 private String content;

	 private Set<CommentDto> comments;
}
