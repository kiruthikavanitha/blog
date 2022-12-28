package com.example.blog.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class PostMeta {
	@Id
	private long metaId;
	private String key;
	private String content;
}
