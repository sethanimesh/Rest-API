package com.sethanimesh.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Animesh Seth");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Animesh", "Seth"));
	}
	
	@GetMapping(path="/person", params="version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Animesh Seth");
	}
	
	@GetMapping(path="/person", params="version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("Animesh", "Seth"));
	}
	
	@GetMapping(path="/person/headers", headers="X-API-VERSIONS=1")
	public PersonV1 getFirstVersionOfPersonHeader() {
		return new PersonV1("Animesh Seth");
	}
	
	@GetMapping(path="/person/headers", headers="X-API-VERSIONS=2")
	public PersonV2 getSecondVersionOfPersonHeader() {
		return new PersonV2(new Name("Animesh", "Seth"));
	}
	
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfAcceptHeader() {
		return new PersonV1("Animesh Seth");
	}
	
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfAcceptHeader() {
		return new PersonV2(new Name("Animesh", "Seth"));
	}
}


