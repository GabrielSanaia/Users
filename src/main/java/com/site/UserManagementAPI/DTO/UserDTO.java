package com.site.UserManagementAPI.DTO;

public class UserDTO {

	private String name;
	private String surname;
	
	public UserDTO(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	
	
	
}
