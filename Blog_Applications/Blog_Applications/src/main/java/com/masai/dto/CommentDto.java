package com.masai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	
	
    private Long id;

   
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

   
    @NotEmpty(message = "Email shoul not be null or empty")
    @Email
    private String email;

    
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 10 caracters")
    private String body;

}
