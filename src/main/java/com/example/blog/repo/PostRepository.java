package com.example.blog.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	 Optional<Post> findByTitle(String postTitle);
}
