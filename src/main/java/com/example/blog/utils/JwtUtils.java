package com.example.blog.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

	@Value("${app.secret}")
	private String secret;
	
	//6.Validate 
	public boolean validateToken(String token,String firstName) {
		String tokenEmail = getFirstName(token);
		return (firstName.equals(tokenEmail) && !isTokenExp(token));
	}
	
	//5.Validate Exp date
	public boolean isTokenExp(String token) {
		Date expDate=getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	
	//4.Read subject/username
	public String getFirstName(String token) {
		return getClaims(token).getSubject();
	}
	
	
	//3.Read Exp Date 
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}
   
	//2.Read claims
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	//1.Generate Token
	public String generateToken(String subject) {
		return Jwts.builder().setSubject(subject)
				.setIssuer("Spring Security")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
}
