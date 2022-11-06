package com.site.UserManagementAPI.Repository;

import java.util.ArrayList;
import java.util.List;

import com.site.UserManagementAPI.DTO.UserDTO;
import com.site.UserManagementAPI.Model.User;

public class UserRepository {

	List<User> users = new ArrayList<>();
	int IDCount = 0;
	
	public void createUser(User user) {
		user.setID(IDCount);
		users.add(user);
		IDCount++;
		
		for(User useri : users)
			System.out.println(useri.getName() + " " + useri.getID());
		
	}

	
	public User getUser(int ID) {
		
		return users.get(getUserIndex(ID));
	}

	
	public User getUser(String name, String surname) {
		return findUser(name, surname);
	}


	public UserDTO getUserDTO(int ID) {
		
		User user = getUser(ID);
		
		UserDTO userDTO = new UserDTO(user.getName(), user.getSurname());
		
		return userDTO;
	}

	
	public List<User> getAllUsers() {
		
		return users;
	}

	
	public void deleteUser(int ID) {
		users.remove(getUserIndex(ID));
		
	}
	

	
	public void editUser(User user) {
		users.set(user.getID(), user);
		
	}
	
	
	public void editUserPartilally(User info) {
		
		User user = users.get(info.getID());
		
		if(info.getUsername() != null)
			user.setUsername(info.getUsername());
		
		if(info.getPassword() != null)
			user.setPassword(info.getPassword());
		
		if(info.getName() != null)
			user.setName(info.getName());
		
		if(info.getSurname() != null)
			user.setSurname(info.getSurname());
		
		if(info.getAge() != 0)
			user.setAge(info.getAge());
		
	}
	
	public List<User> getUsersForPage(int usersPerPage, int pageNumber) {
		
		if((pageNumber - 1) * usersPerPage + usersPerPage <= users.size())
			return users.subList((pageNumber - 1) * usersPerPage, (pageNumber - 1) * usersPerPage + usersPerPage);
		
		return users.subList((pageNumber - 1) * usersPerPage, users.size());
		
		
	}
		
	private int getUserIndex(int id) {
		for(int i = 0; i < users.size(); i++) {
			if(id == users.get(i).getID())
				return i;
		}
		return -1;
	}
	
	private User findUser(String name, String surname) {
		for(int i = 0; i < users.size(); i++) {
			if(name.equals(users.get(i).getName()) &&
					surname.equals(users.get(i).getSurname()))
				return users.get(i);
	}
		return null;
}


	
	
}
