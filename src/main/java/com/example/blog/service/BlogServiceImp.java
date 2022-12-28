package com.example.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.dto.CategoryDto;
import com.example.blog.dto.PostCommentDto;
import com.example.blog.dto.PostDelDto;
import com.example.blog.dto.PostDto;
import com.example.blog.dto.PostMetaDto;
import com.example.blog.dto.PostUpdateDto;

import com.example.blog.dto.TagDto;
import com.example.blog.dto.UserDelDto;
import com.example.blog.dto.UserDto;
import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.PostComment;
import com.example.blog.entity.PostMeta;
import com.example.blog.entity.Tag;
import com.example.blog.entity.User;
import com.example.blog.repo.CategoryRepository;
import com.example.blog.repo.PostCommentRepository;
import com.example.blog.repo.PostMetaRepository;
import com.example.blog.repo.PostRepository;
import com.example.blog.repo.TagRepository;
import com.example.blog.repo.UserRepository;

@Service
public class BlogServiceImp implements BlogService, UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostCommentRepository postCommentRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostMetaRepository metaRepository;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private User user;
	@Autowired
	private Post post;
	@Autowired
	private PostComment postComment;
	@Autowired
	private PostMeta meta;
	@Autowired
	private Tag tag;
	@Autowired
	private Category category;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Override
	public User register(UserDto userDto) {
		BeanUtils.copyProperties(userDto, user);
		user.setPasswordHash(pwdEncoder.encode(userDto.getPasswordHash()));
		userRepository.save(user);
		return user;
	}

	@Override
	public Post postSave(PostDto postDto) {
		User users = userRepository.findById(user.getId()).orElse(null);
		postDto.setUser(users);
		BeanUtils.copyProperties(postDto, post);
		postRepository.save(post);
		return post;
	}

	@Override
	public PostComment postCommentSave(PostCommentDto postCommentDto) {
		Post posts = postRepository.findById(post.getPostId()).orElse(null);
		postCommentDto.setPost(posts);
		BeanUtils.copyProperties(postCommentDto, postComment);
		postCommentRepository.save(postComment);
		return postComment;
	}

	@Override
	public Category categorySave(CategoryDto categoryDto) {
		Post posts = postRepository.findById(post.getPostId()).orElse(null);
		categoryDto.setPost(posts);
		BeanUtils.copyProperties(categoryDto, category);
		categoryRepository.save(category);
		return category;
	}

	@Override
	public User getUser() {

		return user;
	}

	@Override
	public User updateUser(UserDto userDto) {
		User users = userRepository.findById(user.getId()).orElse(null);
		BeanUtils.copyProperties(userDto, users);
		User save = userRepository.save(users);
		if (save != null) {
			return save;
		}
		return null;
	}

	@Override
	public User deleteUser(UserDelDto userDto) {
		userRepository.deleteById(userDto.getId());
		return null;
	}

	@Override
	public List<Post> getPost() {
		List<Post> findAll = postRepository.findAll();
		return findAll;
	}

	@Override
	public Post updatePost(PostUpdateDto postDto) {
		Post posts = postRepository.findById(postDto.getId()).orElse(null);
		BeanUtils.copyProperties(postDto, posts);
		postRepository.save(posts);
		return posts;
	}

	@Override
	public Post deletePost(PostDelDto postDelDto) {
		Post post = postRepository.findById(postDelDto.getPostId()).orElse(null);
		postRepository.delete(post);
		return post;
	}

	@Override
	public Optional<Post> searchPost(String title) {
//		postTitle=title.getTitle();
//		Optional<Post> findByTitle = postRepository.findByTitle(title);
//		if(findByTitle != null) {
//			Post postByTitle = findByTitle.get();
//			return postByTitle;
//		}
//		return null;
		Optional<Post> findByTitle = postRepository.findByTitle(title);
		if (findByTitle.isPresent()) {
			findByTitle.get();
			return findByTitle;
		}
		return null;
	}

	@Override
	public PostMeta postMetaSave(PostMetaDto metaDto) {
		BeanUtils.copyProperties(metaDto, meta);
		metaRepository.save(meta);
		return meta;
	}

	@Override
	public Tag tagSave(TagDto tagDto) {
		BeanUtils.copyProperties(tagDto, tag);
		tagRepository.save(tag);
		return tag;
	}

	@Override
	public List<PostComment> getPostComment() {
		List<PostComment> findAll = postCommentRepository.findAll();
		return findAll;
	}

	@Override
	public PostComment deletePostComment(PostCommentDto postDelDto) {
		PostComment comment = postCommentRepository.findById(postDelDto.getCommentId()).orElse(null);
		postCommentRepository.delete(comment);
		return comment;
	}

	@Override
	public Category getCategory(CategoryDto categoryDto) {
		BeanUtils.copyProperties(categoryDto, category);
		Category id = categoryRepository.findById(categoryDto.getCategoryId()).orElse(null);
		return id;
	}

	@Override
	public Category updateCategory(CategoryDto categoryDto) {
		Category category = categoryRepository.findById(categoryDto.getCategoryId()).orElse(null);
		BeanUtils.copyProperties(categoryDto, category);
		categoryRepository.save(category);
		return category;
	}

	@Override
	public Category deleteCategory(CategoryDto categoryDto) {
		Category category = categoryRepository.findById(categoryDto.getCategoryId()).orElse(null);
		categoryRepository.delete(category);
		return category;
	}

	@Override
	public PostMeta updatePostMeta(PostMetaDto metaDto) {
		PostMeta meta = metaRepository.findById(metaDto.getMetaId()).orElse(null);
		BeanUtils.copyProperties(metaDto, meta);
		metaRepository.save(meta);
		return meta;
	}

	@Override
	public List<PostMeta> getPostMeta() {
		List<PostMeta> findAll = metaRepository.findAll();
		return findAll;
	}

	@Override
	public PostMeta deletePostMeta(PostMetaDto metaDto) {
		PostMeta meta = metaRepository.findById(metaDto.getMetaId()).orElse(null);
		metaRepository.delete(meta);
		return meta;
	}

	@Override
	public List<Category> getCategoryById(CategoryDto categoryDto) {
		List<Category> all = categoryRepository.findAll();
		List<Category> collect = all.stream().filter((i) -> i.getCategoryId() == categoryDto.getCategoryId())
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public Optional<User> findByFirstName(String firstName) {

		return userRepository.findByFirstName(firstName);
	}

	@Override
	public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
		Optional<User> findByFirstName = findByFirstName(firstName);
		if (findByFirstName.isEmpty())
			throw new UsernameNotFoundException("user not exist");
		User user = findByFirstName.get();
		return new org.springframework.security.core.userdetails.User(
				firstName, user.getPasswordHash(), null);
	}

}
