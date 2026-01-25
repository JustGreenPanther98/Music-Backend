package com.songs.wallah.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.DataTransferObject.SongDTO;
import com.songs.wallah.request.SongArtistRequest;
import com.songs.wallah.request.SongCategoryRequest;
import com.songs.wallah.response.SongResponse;
import com.songs.wallah.service.SongService;
import com.songs.wallah.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/songs")
@Tag(name="Song APIs")
public class SongController {
	
	private UserService userService;
	private SongService songService;

	public SongController(UserService userService,SongService songService) {
		this.userService = userService;
		this.songService=songService;
	}
	
	@GetMapping(path="/all", produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<SongResponse> getAllSongs(Authentication authentication){
		
		ModelMapper modelMapper = new ModelMapper();
		List<SongDTO> songs = songService.getSongs();
		List<SongResponse> songResponse = new ArrayList<>();
		for(SongDTO song : songs) {
			songResponse.add(modelMapper.map(song,SongResponse.class));
		}
		return songResponse;
	}
	
	@GetMapping(path="/category", produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<SongResponse> getSongsByCategory(@RequestBody SongCategoryRequest songCategoryRequest ,Authentication authentication){
		List<SongDTO> songDTOs=  songService.getCategorySongs(songCategoryRequest.category().toString());
		List<SongResponse> songResponses = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		for(SongDTO songDTO : songDTOs) {
			songResponses.add(modelMapper.map(songDTO, SongResponse.class));
		}
		return songResponses;
	}
	
	@GetMapping(path="/artist", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<SongResponse> getSongsByArtist(@RequestBody SongArtistRequest songArtistRequest ,Authentication authentication){
		List<SongDTO> songDTOs=  songService.getArtistSongs(songArtistRequest.Artist());
		List<SongResponse> songResponses = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		for(SongDTO songDTO : songDTOs) {
			songResponses.add(modelMapper.map(songDTO, SongResponse.class));
		}
		return songResponses;
	}
	
	
	
}
