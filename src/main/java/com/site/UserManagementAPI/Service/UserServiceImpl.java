package com.site.UserManagementAPI.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.site.UserManagementAPI.DTO.UserDTO;
import com.site.UserManagementAPI.Model.User;
import com.site.UserManagementAPI.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	UserRepository userRepository = new UserRepository();
	
	@Override
	public void createUser(User user) {
		
		userRepository.createUser(user);
	}

	@Override
	public User getUser(int ID) {
		
		return userRepository.getUser(ID);
	}

	@Override
	public User getUser(String name, String surname) {
		
		return userRepository.getUser(name, surname);
	}

	@Override
	public UserDTO getUserDTO(int ID) {
		
		return userRepository.getUserDTO(ID);
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.getAllUsers();
	}

	@Override
	public void deleteUser(int ID) {
		
		userRepository.deleteUser(ID);
	}
	

	@Override
	public void editUser(User user) {
		
		userRepository.editUser(user);
	}

	@Override
	public List<User> getUsersForPage(int usersPerPage, int pageNumber) {
		
		return userRepository.getUsersForPage(usersPerPage, pageNumber);
	}

	@Override
	public void editUserPartilally(User info) {
		
		userRepository.editUserPartilally(info);
	}

}
