package com.songs.wallah.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.configuration.SwaggerConfiguration;
import com.songs.wallah.request.UserSignupRequest;
import com.songs.wallah.response.UserProfile;
import com.songs.wallah.response.UserResponse;
import com.songs.wallah.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final SwaggerConfiguration swaggerConfiguration;

	private UserService userService;

	public UserController(UserService userService, SwaggerConfiguration swaggerConfiguration) {
		this.userService = userService;
		this.swaggerConfiguration = swaggerConfiguration;
	}

	@PostMapping("/signup")
	public UserResponse createAccount(@Valid @RequestBody UserSignupRequest userSignupRequest) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(userSignupRequest.email());
		userDTO.setAge(userSignupRequest.age());
		userDTO.setEncryptedPassword(userSignupRequest.encryptedPassword());
		userDTO.setFirstName(userSignupRequest.firstName());
		userDTO.setMiddleName(userSignupRequest.middleName());
		userDTO.setLastName(userSignupRequest.lastName());
		UserDTO createdUserDTO = userService.createAccount(userDTO);
		UserResponse createdUser = new UserResponse(createdUserDTO.getPublicId(),createdUserDTO.getEmail());
		return createdUser;
	}
	
//	@GetMapping("/me")
//	public UserProfile getProfile(Authentication authentication) {
//		
//	}
}
