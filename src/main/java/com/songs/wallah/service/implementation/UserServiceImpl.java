package com.songs.wallah.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.models.OpenAPI;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.configuration.SwaggerConfiguration;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.enums.otp.OperationStatus;
import com.songs.wallah.repository.UserRepository;
import com.songs.wallah.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	// during login it is being called
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity loggedIn = userRepository.findByEmail(username);
		if (loggedIn == null) {
			throw new UsernameNotFoundException(username);
		}
		if (!loggedIn.isEmailVerification()) {
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
		if (email == null) {
			throw new UsernameNotFoundException("Email doesn't exist");
		}
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User doesn't exist");
		}
		return modelMapper.map(userEntity, UserDTO.class);

	}
	@Override
	public UserDTO updateUser(UserDTO updatedDetails) {
		if(updatedDetails==null) {
			throw new RuntimeException("Invalid login!");
		}
		UserEntity user = userRepository.findByEmail(updatedDetails.getEmail());
		user.setFirstName(updatedDetails.getFirstName());
		user.setLastName(updatedDetails.getLastName());
		user.setMiddleName(updatedDetails.getMiddleName());
		user.setAge(updatedDetails.getAge());
		user = userRepository.save(user);
		UserDTO updatedUser = new UserDTO();
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setMiddleName(user.getMiddleName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setAge(user.getAge());
		return updatedUser;
	}
	
	@Override
	public List<UserDTO> getAllUsers() {
		Iterable<UserEntity> users = userRepository.findAll();
		List<UserDTO> usersDTO = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		for(UserEntity user : users) {
			usersDTO.add(modelMapper.map(user,UserDTO.class));
		}
		return usersDTO;
	}

	@Override
	public OperationStatus deleteUserByEmailAndId(UUID publicId, String email) {
		if(publicId==null || email == null) {
			return OperationStatus.FAIL;
		}
		UserEntity user = userRepository.findByEmail(email);
		if(user==null) {
			return OperationStatus.FAIL;
		}
		if(user.getPublicId()==publicId) {
			return OperationStatus.FAIL;
		}
		userRepository.delete(user);
		return OperationStatus.SUCCESS;
	}
	
	
}
