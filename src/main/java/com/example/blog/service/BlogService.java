package com.example.blog.service;

import java.util.List;
import java.util.Optional;

import com.example.blog.dto.CategoryDto;
import com.example.blog.dto.PostCommentDto;
import com.example.blog.dto.PostDelDto;
import com.example.blog.dto.PostDto;
import com.example.blog.dto.PostMetaDto;
import com.example.blog.dto.PostUpdateDto;
import com.example.blog.dto.SearchByTitleDto;
import com.example.blog.dto.TagDto;
import com.example.blog.dto.UserDelDto;
import com.example.blog.dto.UserDto;
import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.PostComment;
import com.example.blog.entity.PostMeta;
import com.example.blog.entity.Tag;
import com.example.blog.entity.User;

public interface BlogService {

	User register(UserDto userDto);

	Post postSave(PostDto postDto);

	PostComment postCommentSave(PostCommentDto postCommentDto);

	Category categorySave(CategoryDto categoryDto);

	User getUser();

	User updateUser(UserDto userDto);

	User deleteUser(UserDelDto userDto);

	List<Post> getPost();

	Post updatePost(PostUpdateDto postDto);

	Post deletePost(PostDelDto postDelDto);

	PostMeta postMetaSave(PostMetaDto metaDto);

	Tag tagSave(TagDto tagDto);

	List<PostComment> getPostComment();

	PostComment deletePostComment(PostCommentDto postDelDto);

	Category getCategory(CategoryDto categoryDto);

	Category updateCategory(CategoryDto categoryDto);

	Category deleteCategory(CategoryDto categoryDto);

	PostMeta updatePostMeta(PostMetaDto metaDto);

	List<PostMeta> getPostMeta();

	PostMeta deletePostMeta(PostMetaDto metaDto);

	Optional<Post> searchPost(String title);

	List<Category> getCategoryById(CategoryDto categoryDto);
	
	Optional<User> findByFirstName(String firstName);

}
