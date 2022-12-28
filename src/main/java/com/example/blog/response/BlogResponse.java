package com.example.blog.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BlogResponse {
	private String message;
	private boolean error;
	private String status;
	private Object data;
	private String token;
}
