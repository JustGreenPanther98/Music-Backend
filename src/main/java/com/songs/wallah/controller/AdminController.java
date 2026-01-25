package com.songs.wallah.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.DataTransferObject.SongDTO;
import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.request.SongUploadRequest;
import com.songs.wallah.response.SongResponse;
import com.songs.wallah.response.UserProfile;
import com.songs.wallah.service.SongService;
import com.songs.wallah.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/admin")
@Tag(name = "Admin APIs")
public class AdminController {

	private UserService userService;
	private SongService songService;

	public AdminController(UserService userService,SongService songService) {
		this.userService = userService;
		this.songService=songService;
	}

	@GetMapping(path = "/users", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Get all users")
	public List<UserProfile> getUsers() {
		ModelMapper modelMapper = new ModelMapper();
		List<UserDTO> UsersDTO = userService.getAllUsers();
		List<UserProfile> usersProfile = new ArrayList<>();
		for (UserDTO userDTO : UsersDTO) {
			usersProfile.add(new UserProfile(userDTO.getPublicId(), userDTO.getFirstName(), userDTO.getMiddleName(),
					userDTO.getLastName(), userDTO.getAge(), userDTO.getEmail()));
		}
		return usersProfile;
	}

	@PostMapping(path = "/upload-song", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public SongResponse uploadSong(@RequestBody SongUploadRequest songUploadRequest) {

		SongDTO songDTO = new SongDTO(songUploadRequest.songName(), songUploadRequest.artistName(),
				songUploadRequest.category(), songUploadRequest.language(), songUploadRequest.url(),
				songUploadRequest.durationInSeconds(), songUploadRequest.rating());
		SongDTO updatedSongDTO = songService.uploadSong(songDTO);
		return new SongResponse(updatedSongDTO.getPublicId(), updatedSongDTO.getSongName(),
				updatedSongDTO.getArtistName(), updatedSongDTO.getCategory(), updatedSongDTO.getLanguage(),
				updatedSongDTO.getDurationInSeconds(), updatedSongDTO.getRating());

	}
	
	
}
