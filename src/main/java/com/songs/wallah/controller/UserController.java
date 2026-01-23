package com.songs.wallah.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.configuration.SwaggerConfiguration;
import com.songs.wallah.enums.otp.OtpVerification;
import com.songs.wallah.request.EmailVerificationRequest;
import com.songs.wallah.request.UserSignupRequest;
import com.songs.wallah.response.UserResponse;
import com.songs.wallah.service.UserService;
import com.songs.wallah.service.implementation.OtpServiceImplementation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name="User APIs")
public class UserController {

	private final SwaggerConfiguration swaggerConfiguration;
	private UserService userService;
	private OtpServiceImplementation otpServiceImplementation;

	public UserController(UserService userService, SwaggerConfiguration swaggerConfiguration,
			OtpServiceImplementation otpServiceImplementation) {
		this.userService = userService;
		this.swaggerConfiguration = swaggerConfiguration;
		this.otpServiceImplementation = otpServiceImplementation;
	}

	@PostMapping(path="/signup", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public UserResponse createAccount(@Valid @RequestBody UserSignupRequest userSignupRequest) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(userSignupRequest.email());
		userDTO.setAge(userSignupRequest.age());
		userDTO.setEncryptedPassword(userSignupRequest.password());
		userDTO.setFirstName(userSignupRequest.firstName());
		userDTO.setMiddleName(userSignupRequest.middleName());
		userDTO.setLastName(userSignupRequest.lastName());
		otpServiceImplementation.sendOtp(userSignupRequest.email());
		UserDTO createdUserDTO = userService.createAccount(userDTO);
		UserResponse userResponse = new UserResponse(createdUserDTO.getPublicId(),createdUserDTO.getEmail());
		return userResponse;
	}

	@PostMapping(path = "/verify", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public OtpVerification verifyEmail(@RequestBody EmailVerificationRequest emailVerificationRequest) {
		if (userService.getUser(emailVerificationRequest.email()) == null) {
			throw new RuntimeException("Incorrect email");
		}
		return otpServiceImplementation.verifyOtp(emailVerificationRequest.email(),
				emailVerificationRequest.otp().toString());
	}

//	@GetMapping("/me")
//	public UserProfile getProfile(Authentication authentication) {
//		
//	}
}
