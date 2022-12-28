package com.example.blog.dto;

import org.springframework.stereotype.Component;

import com.example.blog.entity.Post;

import lombok.Data;

@Component
@Data
public class CategoryDto {
	private long categoryId;
	private String title;
	private String metaTitle;
	private String slug;
	private String content;
	private Post post;
}
