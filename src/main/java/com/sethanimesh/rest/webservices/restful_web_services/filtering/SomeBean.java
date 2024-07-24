package com.sethanimesh.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties("field1")

@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	
	//@JsonIgnore
	private String field2;
	private String field3;
	
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getValue1() {
		return field1;
	}

	public String getValue2() {
		return field2;
	}

	public String getValue3() {
		return field3;
	}

	@Override
	public String toString() {
		return "SomeBean [value1=" + field1 + ", value2=" + field2 + ", value3=" + field3 + "]";
	}
}
