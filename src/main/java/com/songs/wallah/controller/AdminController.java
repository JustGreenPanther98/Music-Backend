package com.songs.wallah.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfigurationSource;
import com.songs.wallah.DataTransferObject.SongDTO;
import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.configuration.SwaggerConfiguration;
import com.songs.wallah.enums.otp.OperationStatus;
import com.songs.wallah.request.DeleteUserRequest;
import com.songs.wallah.request.SongUploadRequest;
import com.songs.wallah.request.UpdateSongRequest;
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

    private final SwaggerConfiguration swaggerConfiguration;

    private final CorsConfigurationSource corsConfigurationSource;

	private UserService userService;
	private SongService songService;

	public AdminController(UserService userService, SongService songService, CorsConfigurationSource corsConfigurationSource, SwaggerConfiguration swaggerConfiguration) {
		this.userService = userService;
		this.songService = songService;
		this.corsConfigurationSource = corsConfigurationSource;
		this.swaggerConfiguration = swaggerConfiguration;
	}

	@GetMapping(path = "/users", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Get all users")
	public List<UserProfile> getUsers(Authentication authentication) {
		List<UserDTO> UsersDTO = userService.getAllUsers();
		List<UserProfile> usersProfile = new ArrayList<>();
		for (UserDTO userDTO : UsersDTO) {
			usersProfile.add(new UserProfile(userDTO.getPublicId(), userDTO.getFirstName(), userDTO.getMiddleName(),
					userDTO.getLastName(), userDTO.getAge(), userDTO.getEmail()));
		}
		return usersProfile;
	}

	@DeleteMapping(path = "/user-delete", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Deletion of user using public user id and email")
	public OperationStatus deleteUserPermanently(@RequestBody DeleteUserRequest deleteUserRequest,
			Authentication authentication) {
		return userService.deleteUserByEmailAndId(deleteUserRequest.publicId(), deleteUserRequest.email());
	}

	@PostMapping(path = "/upload-song", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Upload song")
	public SongResponse uploadSong(@RequestBody SongUploadRequest songUploadRequest, Authentication authentication) {

		SongDTO songDTO = new SongDTO(songUploadRequest.songName(), songUploadRequest.artistName(),
				songUploadRequest.category(), songUploadRequest.language(), songUploadRequest.url(),
				songUploadRequest.durationInSeconds(), songUploadRequest.rating());

		SongDTO updatedSongDTO = songService.uploadSong(songDTO);

		return new SongResponse(updatedSongDTO.getPublicId(), updatedSongDTO.getSongName(),
				updatedSongDTO.getArtistName(), updatedSongDTO.getCategory(), updatedSongDTO.getLanguage(),
				updatedSongDTO.getDurationInSeconds(), updatedSongDTO.getRating());

	}

	@PostMapping(path = "/upload-songs", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Upload songs")
	public List<SongResponse> uploadSongs(@RequestBody List<SongUploadRequest> songUploadRequests,
			Authentication authentication) {

		List<SongDTO> songDTOs = new ArrayList<>();
		for (SongUploadRequest songUploadRequest : songUploadRequests) {
			System.out.println(songUploadRequest.songName() + " "+ songUploadRequest.artistName());
			
			SongDTO songDTO = new SongDTO(songUploadRequest.songName(), songUploadRequest.artistName(),
					songUploadRequest.category(), songUploadRequest.language(), songUploadRequest.url(),
					songUploadRequest.durationInSeconds(), songUploadRequest.rating());
			
			songDTOs.add(songDTO);
		}

		List<SongDTO> uploadSongDTOs = songService.uploadSong(songDTOs);
		List<SongResponse> songResponses = new ArrayList<>();
		
		for(SongDTO updatedSongDTO : uploadSongDTOs) {
			System.out.println(updatedSongDTO.getSongName() + " "+ updatedSongDTO.getArtistName());

			songResponses.add(new SongResponse(updatedSongDTO.getPublicId(), updatedSongDTO.getSongName(),
					updatedSongDTO.getArtistName(), updatedSongDTO.getCategory(), updatedSongDTO.getLanguage(),
					updatedSongDTO.getDurationInSeconds(), updatedSongDTO.getRating()));
		}
		return songResponses;

	}

	@PutMapping(path = "/update-song", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Upload songs")
	public SongResponse updateSong(@RequestBody UpdateSongRequest updateSongRequest, Authentication authentication) {
		
		SongDTO songDTO = new SongDTO(updateSongRequest.publicId(), updateSongRequest.updatedSongName(),
				updateSongRequest.updatedArtistName(), updateSongRequest.updatedCategory(), updateSongRequest.updatedLanguage(),
				updateSongRequest.updatedUrl(), updateSongRequest.updatedDurationInSeconds(), updateSongRequest.updatedRating());

		SongDTO updatedSongDTO = songService.updateSong(songDTO);

		return new SongResponse(updatedSongDTO.getPublicId(), updatedSongDTO.getSongName(),
				updatedSongDTO.getArtistName(), updatedSongDTO.getCategory(), updatedSongDTO.getLanguage(),
				updatedSongDTO.getDurationInSeconds(), updatedSongDTO.getRating());
	}
	
	
}
