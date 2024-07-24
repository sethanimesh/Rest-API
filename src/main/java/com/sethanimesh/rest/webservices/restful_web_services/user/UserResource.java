package com.sethanimesh.rest.webservices.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sethanimesh.rest.webservices.restful_web_services.jpa.PostRepository;
import com.sethanimesh.rest.webservices.restful_web_services.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserDaoService service;
	
	private UserRepository repository;
	private PostRepository postRepository;

	public UserResource(UserDaoService service, UserRepository repository, PostRepository postRepository) {
		super();
		this.service = service;
		this.repository = repository;
		this.postRepository = postRepository;
	}
	
	
	@GetMapping(path="/jpa/users")
	public List<User> retriveAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping(path="/jpa/users/{id}")
	public EntityModel<Optional<User>> retriveUser(@PathVariable Integer id){
		Optional<User> user = repository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("Id:"+id);
		}
		
		EntityModel<Optional<User>> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);
		// /users/4
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId())
							.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	
	@DeleteMapping(path="/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("Id:"+id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = repository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("Id:"+id);
		}
		
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedPost.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
