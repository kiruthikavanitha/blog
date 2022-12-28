package com.example.blog.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class Tag {
	@Id
	private long tagId;
	private String title;
	private String metaData;
	private String slug;
	private String content;
	
	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "tagid",referencedColumnName = "tagId")
	private Post post;
}
