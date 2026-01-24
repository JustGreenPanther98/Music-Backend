package com.songs.wallah.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.songs.wallah.DataTransferObject.UserDTO;

public interface UserService extends UserDetailsService{
	UserDTO createAccount(UserDTO userDTO);
	UserDTO getUser(String email);
	UserDTO updateUser(UserDTO updatedDetails);
}
