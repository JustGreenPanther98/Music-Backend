package com.songs.wallah.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.enums.otp.OperationStatus;

public interface UserService extends UserDetailsService{
	UserDTO createAccount(UserDTO userDTO);
	UserDTO getUser(String email);
	UserDTO updateUser(UserDTO updatedDetails);
	List<UserDTO> getAllUsers();
	OperationStatus deleteUserByEmailAndId(UUID publicId,String email);
}
