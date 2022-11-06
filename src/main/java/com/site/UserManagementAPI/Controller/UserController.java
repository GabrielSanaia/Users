package com.site.UserManagementAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.site.UserManagementAPI.DTO.UserDTO;
import com.site.UserManagementAPI.Model.ResponseBody;
import com.site.UserManagementAPI.Model.User;
import com.site.UserManagementAPI.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/user")
	public ResponseBody createUser(@RequestBody User user) {
		userService.createUser(user);
		
		HttpStatus status = HttpStatus.OK;
		
		ResponseBody<User> responseBody = new ResponseBody(status.name(), status.value(),
				 "User added!");
				
		ResponseEntity<ResponseBody> responseEntity = new ResponseEntity<ResponseBody>(responseBody, HttpStatus.OK);
		
		
		return responseBody;
	}
	
	@GetMapping("/user/{ID}")
	public ResponseEntity<User> getUser(@PathVariable String ID) {
		User user = userService.getUser(Integer.parseInt(ID));
		
		HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<User>(user,  HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.getAllUsers();
		
		ResponseEntity<List<User>> responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);
		
		return responseEntity;
	}
	
	@GetMapping("/userdto/{ID}")
	public ResponseEntity<UserDTO> getUserDTO(@PathVariable String ID) {
		
		UserDTO userDTO = userService.getUserDTO(Integer.parseInt(ID));
		
		System.out.println(userDTO.getName());
		System.out.println(userDTO.getSurname());
		
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<User> getUser(@RequestParam String name, @RequestParam String surname) {
		User user = userService.getUser(name, surname);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/user")
	public void deleteUser(@RequestBody User user) {
		
		userService.deleteUser(user.getID());
	}
	
	@PutMapping("/user")
	public void editUser(@RequestBody User user) {
		userService.editUser(user);
	}
	
	@PatchMapping("/user")
	public void editUserPartially(@RequestBody User user) {
		
		userService.editUserPartilally(user);
	}
	
	@GetMapping("/userspage")
	public ResponseEntity<List<User>> getUsersForPage(@RequestParam int usersPerPage, @RequestParam int pageNumber ) {
		
		return new ResponseEntity<List<User>>(userService.getUsersForPage(usersPerPage, pageNumber), HttpStatus.OK);
	}
	
}
