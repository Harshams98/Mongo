package com.mongo.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resourcename;
	
	private String fieldname;
	
	private Object fieldvalue;

	public ResourceNotFoundException(String resourcename, String fieldname, Object fieldvalue) {
		super();
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
		System.out.println(String.format("%s not found with %s: '%s'"+
		fieldname, getStackTrace(),fieldvalue));
	}

	public String getResourcename() {
		return resourcename;
	}


	public String getFieldname() {
		return fieldname;
	}


	public String getFieldvalue() {
		return (String)fieldvalue;
	}

}
