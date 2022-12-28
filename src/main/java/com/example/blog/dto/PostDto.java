package com.example.blog.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.blog.entity.User;

import lombok.Data;
@Component
@Data
public class PostDto {
	private long postId;
	private String title;
	private String metaTitle;
	private String slug;
	private String summary;
	private String published;
	private Date createdAt;
	private Date updatedAt;
	private Date publishedAt;
	private String content;
	private User user;
}
