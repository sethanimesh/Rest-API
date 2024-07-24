package com.sethanimesh.rest.webservices.restful_web_services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sethanimesh.rest.webservices.restful_web_services.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
}
