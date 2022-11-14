package com.site.UserManagementAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	public ResponseEntity<ResponseBody> createUser(@RequestBody User user) {
		userService.createUser(user);
		HttpStatus status = HttpStatus.CREATED;
		
		ResponseBody<String> responseBody = new ResponseBody(status.name(), status.value(),
				 "User added!");
				
		return new ResponseEntity<ResponseBody>(responseBody, status);
	}
	
	@GetMapping("/user/{ID}")
//	@CrossOrigin("http://localhost:8093/")
	public ResponseEntity<ResponseBody<User>> getUser(@PathVariable String ID) {
		User user = userService.getUser(Integer.parseInt(ID));
		
		HttpStatus status = HttpStatus.OK;
		ResponseBody responseBody = new ResponseBody<User>(status.name(), status.value(), user);
		
		System.out.println("Inside API: " + status.name() + " " + status.value() + " " + user);
		
		return new ResponseEntity<ResponseBody<User>>(responseBody, status);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseBody<List<User>>> getUsers() {
		List<User> users = userService.getAllUsers();
		
		HttpStatus status = HttpStatus.OK;
		
		ResponseBody<List<User>> responseBody = new ResponseBody(status.name(), status.value(), users);
		
		return new ResponseEntity<ResponseBody<List<User>>>(responseBody, status);
	}
	
	@GetMapping("/userdto/{ID}")
	public ResponseEntity<ResponseBody<UserDTO>> getUserDTO(@PathVariable String ID) {
		
		UserDTO userDTO = userService.getUserDTO(Integer.parseInt(ID));
		
		HttpStatus status = HttpStatus.OK;
		
		ResponseBody responseBody = new ResponseBody(status.name(), status.value(), userDTO);
		
		return new ResponseEntity<ResponseBody<UserDTO>>(responseBody, status);
	}
	
	@GetMapping("/user")
	public ResponseEntity<ResponseBody<User>> getUser(@RequestParam String name, @RequestParam String surname) {
		User user = userService.getUser(name, surname);
		
		HttpStatus status = HttpStatus.OK;
		
		ResponseBody responseBody = new ResponseBody(status.name(), status.value(), user);
		return new ResponseEntity<ResponseBody<User>>(responseBody, status);
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<ResponseBody<String>> deleteUser(@RequestBody User user) {
		
		userService.deleteUser(user.getID());
		
		HttpStatus status = HttpStatus.OK;
		
		ResponseBody responseBody = new ResponseBody<String>(status.name(), status.value(), "User deleted succesfully!");
		return new ResponseEntity<ResponseBody<String>>(responseBody, status);
	}

	@PutMapping("/user")
	public ResponseEntity<ResponseBody<String>> editUser(@RequestBody User user) {
		
		boolean result = userService.editUser(user);
		
		HttpStatus status;
		ResponseBody responseBody;
		
		if(result) {
			status = HttpStatus.OK;
			responseBody = new ResponseBody(status.name(), status.value(), "User update Succesfully!");
		}else {
			status = HttpStatus.BAD_REQUEST;
			responseBody = new ResponseBody(status.name(), status.value(), "Illegal ID!");
		}
		
		return new ResponseEntity<ResponseBody<String>>(responseBody, status);
	}
	
	@PatchMapping("/user")
	public ResponseEntity<ResponseBody<User>> editUserPartially(@RequestBody User user) {
		
		userService.editUserPartilally(user);
		
		user = userService.getUser(user.getID());
		
		HttpStatus status = HttpStatus.OK;
		ResponseBody responseBody = new ResponseBody(status.name(), status.value(), user);
		
		return new ResponseEntity<ResponseBody<User>>(responseBody, status);
	}
	
	@GetMapping("/userspage")
	public ResponseEntity<ResponseBody<List<User>>> getUsersForPage(@RequestParam int usersPerPage, @RequestParam int pageNumber) {
		List<User> users = userService.getUsersForPage(usersPerPage, pageNumber);
		
		HttpStatus status = HttpStatus.OK;
		
		ResponseBody<List<User>> responseBody = new ResponseBody<List<User>>(status.name(), status.value(), users);
		
		return new ResponseEntity<ResponseBody<List<User>>>(responseBody, status);
	}
	
}
