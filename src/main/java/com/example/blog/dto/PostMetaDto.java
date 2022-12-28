package com.example.blog.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PostMetaDto {
	private long metaId;
	private String key;
	private String content;
}
