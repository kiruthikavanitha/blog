package com.example.blog.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TagDto {
	private long tagId;
	private String title;
	private String metaData;
	private String slug;
	private String content;
}
