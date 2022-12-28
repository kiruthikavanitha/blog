package com.example.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.blog.utils.JwtUtils;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserDetailsService detailsService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		 String token=request.getHeader("Authorization");
		 if(token!=null) {
			 String firstName = jwtUtils.getFirstName(token);
			 if(firstName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				 UserDetails username = detailsService.loadUserByUsername(firstName);
				 //validate token
				 boolean isValid = jwtUtils.validateToken(token, username.getUsername());
				 if(isValid) {
					 UsernamePasswordAuthenticationToken  authToken = 
							 new UsernamePasswordAuthenticationToken(
									 firstName, username.getPassword());
					 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					 SecurityContextHolder.getContext().setAuthentication(authToken);
				 }
			 }
		 }
		 filterChain.doFilter(request, response);
		
	}

}
