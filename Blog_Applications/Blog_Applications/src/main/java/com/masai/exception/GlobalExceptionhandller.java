package com.masai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpHeaders;

//import com.healthyswad.exception.MyErrorDetails;
import com.masai.dto.ErrorDetails;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class GlobalExceptionhandller extends ResponseEntityExceptionHandler{
	

	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourceNotFoundException exception,
	                                                                         WebRequest req){
		    ErrorDetails me = new ErrorDetails();
			me.setTimestamp(LocalDateTime.now());
			me.setMessage(exception.getMessage());
			me.setDescription(req.getDescription(false));
			
			return new ResponseEntity<ErrorDetails>(me,HttpStatus.BAD_REQUEST);
	    }
	 
	 
	 @ExceptionHandler(BlogAPIException.class)
	    public ResponseEntity<ErrorDetails> handlerBlogAPIException(BlogAPIException exception,
	                                                                         WebRequest req){
		 ErrorDetails me = new ErrorDetails();
			me.setTimestamp(LocalDateTime.now());
			me.setMessage(exception.getMessage());
			me.setDescription(req.getDescription(false));
			
			return new ResponseEntity<ErrorDetails>(me,HttpStatus.BAD_REQUEST);
	    }
	 
	 
	 @ExceptionHandler(Exception.class)
		public ResponseEntity<ErrorDetails> ExceptionHandler(Exception ce,WebRequest req) {
				
		 ErrorDetails me = new ErrorDetails();
			me.setTimestamp(LocalDateTime.now());
			me.setMessage(ce.getMessage());
			me.setDescription(req.getDescription(false));
			
			return new ResponseEntity<ErrorDetails>(me,HttpStatus.BAD_REQUEST);
			
			
		}
	 
//	 @Override
//	 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,HttpStatus status,WebRequest request){
//	                                                                    
//	        Map<String, String> errors = new HashMap<>();
//	        ex.getBindingResult().getAllErrors().forEach( (err) -> {
//	            String fieldName = ((FieldError)err).getField();
//	            String message = err.getDefaultMessage();
//	            errors.put(fieldName, message);
//	        } );
//	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	    }

}
