package com.example.blog.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class User {
	@Id
	private long id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String middletName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private int mobile;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String passwordHash;
	@Column(nullable = false)
	private Date registerAt;
	@Column(nullable = false)
	private Date lastLogin;
	@Column(nullable = false)
	private String intro;
	@Column(nullable = false)
	private String profile;

}
