package com.songs.wallah.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.response.UserProfile;
import com.songs.wallah.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs")
public class AdminController {
	private UserService userService;
	public AdminController(UserService userService) {
		this.userService=userService;
	}
	@GetMapping(path="/users", produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Get all users")
	public List<UserProfile> getUsers() {
		ModelMapper modelMapper = new ModelMapper();
		List<UserDTO> UsersDTO = userService.getAllUsers();
		List<UserProfile> usersProfile = new ArrayList<>();
		for(UserDTO userDTO : UsersDTO ) {
			usersProfile.add(new UserProfile(userDTO.getFirstName(),userDTO.getMiddleName(),userDTO.getLastName(),userDTO.getAge(),userDTO.getEmail()));
		}
		return usersProfile;
	}
	
}
