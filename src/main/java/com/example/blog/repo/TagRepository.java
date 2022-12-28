package com.example.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

}
