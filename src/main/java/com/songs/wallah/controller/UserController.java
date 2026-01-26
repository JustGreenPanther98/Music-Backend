package com.songs.wallah.controller;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.enums.otp.OperationStatus;
import com.songs.wallah.enums.otp.OtpVerification;
import com.songs.wallah.enums.songs.Role;
import com.songs.wallah.request.EmailVerificationRequest;
import com.songs.wallah.request.ResendOtpRequest;
import com.songs.wallah.request.UserSignupRequest;
import com.songs.wallah.request.UserUpdateRequest;
import com.songs.wallah.response.UserProfile;
import com.songs.wallah.response.UserResponse;
import com.songs.wallah.service.OtpService;
import com.songs.wallah.service.UserService;
import com.songs.wallah.service.implementation.EmailSenderImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User APIs")
@CrossOrigin("*")
public class UserController {

    private final EmailSenderImpl emailSenderImpl;

	private final AuthenticationManager authenticationManager;
	private UserService userService;
	private OtpService otpService;

	public UserController(UserService userService, OtpService otpService,
			AuthenticationManager authenticationManager, EmailSenderImpl emailSenderImpl) {
		this.userService = userService;
		this.otpService = otpService;
		this.authenticationManager = authenticationManager;
		this.emailSenderImpl = emailSenderImpl;
	}

	@PostMapping(path = "/signup", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Creation of account")
	public UserResponse createAccount(@Valid @RequestBody UserSignupRequest userSignupRequest) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(userSignupRequest.email());
		userDTO.setAge(userSignupRequest.age());
		userDTO.setEncryptedPassword(userSignupRequest.password());
		userDTO.setFirstName(userSignupRequest.firstName());
		userDTO.setMiddleName(userSignupRequest.middleName());
		userDTO.setLastName(userSignupRequest.lastName());
		userDTO.setRole(Role.USER);
		otpService.sendOtp(userSignupRequest.email());
		UserDTO createdUserDTO = userService.createAccount(userDTO);
		UserResponse userResponse = new UserResponse(createdUserDTO.getPublicId(), createdUserDTO.getEmail());
		return userResponse;
	}
	
	@PostMapping(path="/resend-otp", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Resend OTP", description = "(Resend OTP to email which is already being signup (Latest OTP will get accepted))")
	public OperationStatus resendOtp(@RequestBody ResendOtpRequest otpResend) {
		return otpService.resendOtp(otpResend.email());
	}

	@PostMapping(path = "/verify", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "It Verifies account using OTP", description = "(It takes email,otp in form of json or xml and returns(OTP_EXPIRED/INVALID_OTP/INVALID_EMAIL/SUCCESS/ERROR), Without email verification the user can't login)")
	public OtpVerification verifyEmail(@RequestBody EmailVerificationRequest emailVerificationRequest) {
		return otpService.verifyOtp(emailVerificationRequest.email(),
				emailVerificationRequest.otp().toString());
	}

	@GetMapping(path = "/me")
	@Operation(summary = "Returns User's Details (LOGIN REQUIRED)", description = "(It returns whole user profile [but NOT password,email])")
	public UserProfile getProfile(Authentication authentication) {
		UserDTO userDTO = userService.getUser(authentication.getName());
		UserProfile userProfile = new UserProfile(userDTO.getPublicId(), userDTO.getFirstName(), userDTO.getMiddleName(),
				userDTO.getLastName(), userDTO.getAge(), userDTO.getEmail());
		return userProfile;

	}

	@PutMapping(path = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Update User's Details (LOGIN REQUIRED)", description = "(It takes whole profile for updation, but you can only update first name,middle name,last name,age)")
	public UserUpdateRequest updateProfile(@RequestBody UserUpdateRequest updatedProfile,
			Authentication authentication) {

		UserDTO userDTO = new UserDTO();

		userDTO.setFirstName(updatedProfile.firstName());
		userDTO.setLastName(updatedProfile.lastName());
		userDTO.setMiddleName(updatedProfile.middleName());
		userDTO.setAge(updatedProfile.age());
		userDTO.setEmail(authentication.getName());
		UserDTO updatedUserDTO = userService.updateUser(userDTO);
		UserUpdateRequest userUpdateRequest = new UserUpdateRequest(updatedUserDTO.getFirstName(),
				updatedUserDTO.getMiddleName(), updatedUserDTO.getLastName(), updatedUserDTO.getAge());
		return userUpdateRequest;
	}

}
