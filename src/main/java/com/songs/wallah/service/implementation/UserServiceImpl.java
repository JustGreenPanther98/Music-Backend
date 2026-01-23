package com.songs.wallah.service.implementation;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.configuration.SwaggerConfiguration;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.repository.UserRepository;
import com.songs.wallah.service.UserService;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Service
public class UserServiceImpl implements UserService {

    private final SwaggerConfiguration swaggerConfiguration;

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SwaggerConfiguration swaggerConfiguration) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.swaggerConfiguration = swaggerConfiguration;
	}

	// during login it is being called
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity loggedIn = userRepository.findByEmail(username);
		if (loggedIn == null) {
			throw new UsernameNotFoundException(username);
		}
		if(!loggedIn.isEmailVerification()) {
			throw new RuntimeException("Email isn't verified");
		}
		// User constructor takes user name,encrypted password gets converted in
		// password and list of granted authority
		// username -> principle , login password , authorities -> array
		return new User(username, loggedIn.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDTO createAccount(UserDTO userDTO) {
		UserEntity user = userRepository.findByEmail(userDTO.getEmail());
		if (user != null) {
			throw new RuntimeException("User already exist!");
		}
		UserEntity createUser = new UserEntity();
		ModelMapper modelMapper = new ModelMapper();
		createUser = modelMapper.map(userDTO, UserEntity.class);
		createUser.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getEncryptedPassword()));
		createUser = userRepository.save(createUser);
		return modelMapper.map(createUser, UserDTO.class);
	}

	@Override
	public UserDTO getUser(String email) {
		
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity==null) {
			throw new UsernameNotFoundException("User doesn't exist");
		}
		return modelMapper.map(userEntity, UserDTO.class);
		
	}

}
