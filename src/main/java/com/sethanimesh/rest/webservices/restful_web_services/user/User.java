package com.sethanimesh.rest.webservices.restful_web_services.user;

import java.time.LocalDate;

import org.springframework.stereotype.Component;


public class User {
	
	private Integer id;
	private String name;
	private LocalDate birthDate;
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "user [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
}