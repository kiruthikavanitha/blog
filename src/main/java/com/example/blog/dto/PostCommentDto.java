package com.example.blog.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.blog.entity.Post;

import lombok.Data;
@Component
@Data
public class PostCommentDto {
	private long commentId;
	private String title;
	private Integer published;
	private Date createdAt;
	private Date publishedAt;
	private String content;
	private Post post;
}
