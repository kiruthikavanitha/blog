package com.example.blog.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class UserDto {
	private long id;
	private String firstName;
	private String middletName;
	private String lastName;
	private int mobile;
	private String email;
	private String passwordHash;
	private Date registerAt;
	private Date lastLogin;
	private String intro;
	private String profile;
}
