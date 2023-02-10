package com.masai.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{
	
	private HttpStatus status;
    private String messagem;

    public BlogAPIException(HttpStatus status, String messagem) {
        this.status = status;
        this.messagem = messagem;
    }

    public BlogAPIException(String message, HttpStatus status, String messagem) {
        super(message);
        this.status = status;
        this.messagem = messagem;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessagem() {
        return messagem;
    }

}
