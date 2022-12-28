package com.example.blog.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class Post {
	@Id
	private long postId;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String metaTitle;
	@Column(nullable = false)
	private String slug;
	@Column(nullable = false)
	private String summary;
	@Column(nullable = false)
	private String published;
	@Column(nullable = false)
	private Date createdAt;
	@Column(nullable = false)
	private Date updatedAt;
	@Column(nullable = false)
	private Date publishedAt;
	@Column(nullable = false)
	private String content;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "authorid",referencedColumnName = "id")
	private User user;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "parentid",referencedColumnName = "postId")
	private List<Post> posts;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "postid",referencedColumnName = "postId")
	private List<PostMeta> postMeta;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = @JoinColumn(name = "postid",referencedColumnName = "postId"),
	inverseJoinColumns =@JoinColumn(name = "tagid",referencedColumnName = "tagId") )
	private List<Tag> tag;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = @JoinColumn(name = "postid",referencedColumnName = "postId"),
	inverseJoinColumns =@JoinColumn(name = "categoryid",referencedColumnName = "categoryId") )
	private List<Category> categories;
	
}
