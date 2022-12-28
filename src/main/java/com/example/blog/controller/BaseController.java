package com.example.blog.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.CategoryDto;
import com.example.blog.dto.LoginDto;
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
import com.example.blog.exception.InvalidDataException;
import com.example.blog.response.BlogResponse;
import com.example.blog.service.BlogService;
import com.example.blog.utils.JwtUtils;

@RestController
@RequestMapping("/usercontroller")
public class BaseController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogResponse blogResponse;
	@Autowired
	private JwtUtils utils;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login ")
	public ResponseEntity<BlogResponse> login(@RequestBody LoginDto loginDto){
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDto.getFirstName(), loginDto.getPasswordHash()));
		
		String token = utils.generateToken(loginDto.getFirstName());
		blogResponse.setMessage("success");
		blogResponse.setData(token);
		return new ResponseEntity<BlogResponse>(blogResponse,HttpStatus.OK);
	}

	@PostMapping("/register")
	private ResponseEntity<BlogResponse> register(@RequestBody UserDto userDto) {
		User register = blogService.register(userDto);
		if (register != null) {
			blogResponse.setMessage("account created");
			blogResponse.setStatus("200");
			blogResponse.setData(register);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			throw new InvalidDataException("invalid data");
		}
	}

	@PostMapping("/postsave")
	private ResponseEntity<BlogResponse> postSave(@RequestBody PostDto postDto) {
		Post postSave = blogService.postSave(postDto);
		if (postSave != null) {
			blogResponse.setMessage("post created");
			blogResponse.setStatus("200");
			blogResponse.setData(postSave);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			throw new InvalidDataException("invalid data");
		}
	}

	@PostMapping("/postcommentsave")
	private ResponseEntity<BlogResponse> postCommentSave(@RequestBody PostCommentDto postCommentDto) {
		PostComment postComment = blogService.postCommentSave(postCommentDto);
		if (postComment != null) {
			blogResponse.setMessage("postcomment created");
			blogResponse.setStatus("200");
			blogResponse.setData(postComment);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/Categorysave")
	private ResponseEntity<BlogResponse> categorySave(@RequestBody CategoryDto categoryDto) {
		 Category categorySave = blogService.categorySave(categoryDto);
		if (categorySave != null) {
			blogResponse.setMessage("Category created");
			blogResponse.setStatus("200");
			blogResponse.setData(categorySave);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	@PostMapping("/postmetasave")
	private ResponseEntity<BlogResponse> postMetaSave(@RequestBody PostMetaDto metaDto) {
		 PostMeta meta = blogService.postMetaSave(metaDto);
		if (meta != null) {
			blogResponse.setMessage("postmeta created");
			blogResponse.setStatus("200");
			blogResponse.setData(meta);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	@PostMapping("/tagsave")
	private ResponseEntity<BlogResponse> tagSave(@RequestBody TagDto tagDto) {
		 Tag tag = blogService.tagSave(tagDto);
		if (tag != null) {
			blogResponse.setMessage("tag created");
			blogResponse.setStatus("200");
			blogResponse.setData(tag);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	
//user api's
	
	@GetMapping("/getuser")
	private ResponseEntity<BlogResponse> getUser() {
		 User user = blogService.getUser();
		if (user != null) {
			blogResponse.setMessage("got user details");
			blogResponse.setStatus("200");
			blogResponse.setData(user);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			throw new InvalidDataException("invalid data");
		}
}
	@PutMapping("/updateuser")
	private ResponseEntity<BlogResponse> updateUser(@RequestBody UserDto userDto) {
		 User user = blogService.updateUser(userDto);
		if (user != null) {
			blogResponse.setMessage("user updated");
			blogResponse.setStatus("200");
			blogResponse.setData(user);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			throw new InvalidDataException("invalid data");
		}
}
	@DeleteMapping("/deleteuser")
	private ResponseEntity<BlogResponse> deleteUser(@RequestBody UserDelDto userDto) {
		 User user = blogService.deleteUser(userDto);
		if (user == null) {
			blogResponse.setMessage("user details deleted");
			blogResponse.setStatus("200");
			blogResponse.setData(user);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
} 
	//post api's
	
	@GetMapping("/getpost")
	private ResponseEntity<BlogResponse> getPost() {
		  List<Post> post = blogService.getPost();
		if (post != null) {
			blogResponse.setMessage("got user details");
			blogResponse.setStatus("200");
			blogResponse.setData(post);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			throw new InvalidDataException("invalid data");
		}
}
	@PutMapping("/updatepost")
	private ResponseEntity<BlogResponse> updatePost(@RequestBody PostUpdateDto postDto) {
		 Post post = blogService.updatePost(postDto);
		if (post != null) {
			blogResponse.setMessage("user updated");
			blogResponse.setStatus("200");
			blogResponse.setData(post);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			throw new InvalidDataException("invalid data");
		}
}
	@DeleteMapping("/deletepost")
	private ResponseEntity<BlogResponse> deletePost(@RequestBody PostDelDto postDelDto) {
		 Post post = blogService.deletePost(postDelDto);
		if (post == null) {
			blogResponse.setMessage("post details deleted");
			blogResponse.setStatus("200");
			blogResponse.setData(post);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	@GetMapping("/searchpost/{title}")
	private ResponseEntity<BlogResponse> searchPost(@PathVariable String title) {
		 Optional<Post> post = blogService.searchPost(title);
		if (post != null) {
			blogResponse.setMessage("got post by title");
			blogResponse.setStatus("200");
			blogResponse.setData(post);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			throw new InvalidDataException("invalid title");
		}
}
	
	//postcomment api's
	
	@GetMapping("/getpostcomment")
	private ResponseEntity<BlogResponse> getPostComment() {
		  List<PostComment> post = blogService.getPostComment();
		if (post != null) {
			blogResponse.setMessage("got comments");
			blogResponse.setStatus("200");
			blogResponse.setData(post);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	
	@DeleteMapping("/deletepostcomment")
	private ResponseEntity<BlogResponse> deletePostComment(@RequestBody PostCommentDto postDelDto) {
		 PostComment post = blogService.deletePostComment(postDelDto);
		if (post == null) {
			blogResponse.setMessage("comments deleted");
			blogResponse.setStatus("200");
			blogResponse.setData(post);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	//category api's
	
	@GetMapping("/getcategory")
	private ResponseEntity<BlogResponse> getCategory(@RequestBody CategoryDto categoryDto ) {
		Category category= blogService.getCategory(categoryDto);
		if (category != null) {
			blogResponse.setMessage("got category details");
			blogResponse.setStatus("200");
			blogResponse.setData(category);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	@GetMapping("/searchcategorybyid")
	private ResponseEntity<BlogResponse> getCategoryById(@RequestBody CategoryDto categoryDto ) {
		List<Category>  category= blogService.getCategoryById(categoryDto);
		if (category != null) {
			blogResponse.setMessage("got category details");
			blogResponse.setStatus("200");
			blogResponse.setData(category);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	@PutMapping("/updatecategory")
	private ResponseEntity<BlogResponse> updateCategory(@RequestBody CategoryDto categoryDto) {
		Category category = blogService.updateCategory(categoryDto);
		if (category != null) {
			blogResponse.setMessage("category updated");
			blogResponse.setStatus("200");
			blogResponse.setData(category);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	@DeleteMapping("/deletecategory")
	private ResponseEntity<BlogResponse> deleteCategory(@RequestBody CategoryDto categoryDto) {
		Category category  = blogService.deleteCategory(categoryDto);
		if (category == null) {
			blogResponse.setMessage("category deleted");
			blogResponse.setStatus("200");
			blogResponse.setData(category);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
} 
	//postmeta api's
	
	@GetMapping("/getpostmeta")
	private ResponseEntity<BlogResponse> getPostMeta() {
		  List<PostMeta > post = blogService.getPostMeta();
		if (post != null) {
			blogResponse.setMessage("got user details");
			blogResponse.setStatus("200");
			blogResponse.setData(post);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	@PutMapping("/updatepostmeta")
	private ResponseEntity<BlogResponse> updatePostMeta(@RequestBody PostMetaDto metaDto) {
		 PostMeta post = blogService.updatePostMeta(metaDto);
		if (post != null) {
			blogResponse.setMessage("user updated");
			blogResponse.setStatus("200");
			blogResponse.setData(post);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
	@DeleteMapping("/deletepostmeta")
	private ResponseEntity<BlogResponse> deletePostMeta(@RequestBody PostMetaDto metaDto) {
		PostMeta post = blogService.deletePostMeta(metaDto);
		if (post == null) {
			blogResponse.setMessage("post details deleted");
			blogResponse.setStatus("200");
			blogResponse.setData(post);
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.ACCEPTED);
		} else {
			blogResponse.setMessage("something went wrong");
			return new ResponseEntity<BlogResponse>(blogResponse, HttpStatus.BAD_REQUEST);
		}
}
}

