package com.example.blog.entity;

import java.util.Date;
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
public class PostComment {
	@Id
	private long commentId;
	private String title;
	private Integer published;
	private Date createdAt;
	private Date publishedAt;
	private String content;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "parentid",referencedColumnName = "commentId")
//	private List<PostComment> postComments;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "postid",referencedColumnName = "postId")
	private Post post;
}
