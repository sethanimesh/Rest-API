package com.sethanimesh.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>(); 
	private static int usersCount=0;
	
	static {
		users.add(new User(++usersCount, "Animesh", LocalDate.now().minusYears(21)));
		users.add(new User(++usersCount, "Dummy2", LocalDate.now().minusYears(18)));
		users.add(new User(++usersCount, "Dummy3", LocalDate.now().minusYears(22)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
		
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		users.removeIf(predicate);
	}
}
