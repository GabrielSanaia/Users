package com.site.UserManagementAPI.Service;

import java.util.List;

import com.site.UserManagementAPI.DTO.UserDTO;
import com.site.UserManagementAPI.Model.User;

public interface UserService {

	public void createUser(User user);
	
	public User getUser(int ID);
	
	public User getUser(String name, String surname);
		
	public UserDTO getUserDTO(int ID);
	
	public List<User> getAllUsers();
	
	public boolean editUser(User user);
	
	public void editUserPartilally(User info);
	
	public List<User> getUsersForPage(int usersPerPage, int pageNumber);
	
	public void deleteUser(int ID);
	
	
}
