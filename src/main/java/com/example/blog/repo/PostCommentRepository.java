 package com.example.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long>{

}
