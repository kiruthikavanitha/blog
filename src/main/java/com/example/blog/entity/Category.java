package com.example.blog.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class Category {
	@Id
	private long categoryId;
	private String title;
	private String metaTitle;
	private String slug;
	private String content;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "parentid",referencedColumnName = "categoryId")
	private List<Category> categories;
	
	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "categoryid",referencedColumnName = "categoryId")
	private Post post;

}
