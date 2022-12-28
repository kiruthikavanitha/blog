package com.example.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.PostMeta;

public interface PostMetaRepository extends JpaRepository<PostMeta, Long>{

}
