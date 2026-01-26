package com.songs.wallah.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.DataTransferObject.SongDTO;
import com.songs.wallah.request.SongArtistRequest;
import com.songs.wallah.request.SongCategoryRequest;
import com.songs.wallah.request.SongLanguageRequest;
import com.songs.wallah.response.SongResponse;
import com.songs.wallah.service.SongService;
import com.songs.wallah.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/songs")
@Tag(name = "Song APIs")
public class SongController {

	private SongService songService;

	public SongController(SongService songService) {
		this.songService = songService;
	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "It gives all songs")
	public List<SongResponse> getAllSongs(Authentication authentication) {

		List<SongDTO> songDTOs = songService.getSongs();
		List<SongResponse> songResponses = new ArrayList<>();
		for (SongDTO songDTO : songDTOs) {
			songResponses.add(new SongResponse(songDTO.getPublicId(), songDTO.getSongName(), songDTO.getArtistName(),
					songDTO.getCategory(), songDTO.getLanguage(), songDTO.getDurationInSeconds(), songDTO.getRating()));
		}
		return songResponses;
	}

	@GetMapping(path = "/category", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "It gives songs on the basis of category")
	public List<SongResponse> getSongsByCategory(@RequestBody SongCategoryRequest songCategoryRequest,
			Authentication authentication) {
		List<SongDTO> songDTOs = songService.getCategorySongs(songCategoryRequest.category().toString());
		List<SongResponse> songResponses = new ArrayList<>();
		for (SongDTO songDTO : songDTOs) {
			songResponses.add(new SongResponse(songDTO.getPublicId(), songDTO.getSongName(), songDTO.getArtistName(),
					songDTO.getCategory(), songDTO.getLanguage(), songDTO.getDurationInSeconds(), songDTO.getRating()));
		}
		return songResponses;
	}

	@GetMapping(path = "/artist", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "It gives songs on the basis of artist")
	public List<SongResponse> getSongsByArtist(@RequestBody SongArtistRequest songArtistRequest,
			Authentication authentication) {
		List<SongDTO> songDTOs = songService.getArtistSongs(songArtistRequest.Artist());
		List<SongResponse> songResponses = new ArrayList<>();
		for (SongDTO songDTO : songDTOs) {
			songResponses.add(new SongResponse(songDTO.getPublicId(), songDTO.getSongName(), songDTO.getArtistName(),
					songDTO.getCategory(), songDTO.getLanguage(), songDTO.getDurationInSeconds(), songDTO.getRating()));
		}
		return songResponses;
	}
	
	@GetMapping(path="/language", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "It gives songs on the basis of languageW")
	public List<SongResponse> getSongsByLanguage(@RequestBody SongLanguageRequest songLanguageRequest, Authentication authentication){
		List<SongDTO> songDTOs  = songService.getLanguageSongs(songLanguageRequest.langauge().toString());
		List<SongResponse> songResponses = new ArrayList<>();
		for (SongDTO songDTO : songDTOs) {
			songResponses.add(new SongResponse(songDTO.getPublicId(), songDTO.getSongName(), songDTO.getArtistName(),
					songDTO.getCategory(), songDTO.getLanguage(), songDTO.getDurationInSeconds(), songDTO.getRating()));
		}
		return songResponses;
	}

}
