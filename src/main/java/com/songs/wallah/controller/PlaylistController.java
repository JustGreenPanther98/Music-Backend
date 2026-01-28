package com.songs.wallah.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.DataTransferObject.PlaylistDTO;
import com.songs.wallah.DataTransferObject.SongDTO;
import com.songs.wallah.enums.songs.PlayListAccess;
import com.songs.wallah.request.PlaylistCreationRequest;
import com.songs.wallah.response.CreatedPlaylistResponse;
import com.songs.wallah.response.PlaylistResponses;
import com.songs.wallah.service.PlaylistService;
import com.songs.wallah.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/playlists")
@Tag(name = "Playlist APIs")
public class PlaylistController {

	private PlaylistService playlistService;
	private UserService userService;

	public PlaylistController(PlaylistService playlistService, UserService userService) {
		this.playlistService = playlistService;
		this.userService = userService;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public CreatedPlaylistResponse createPlaylist(@RequestBody PlaylistCreationRequest playlistCreationRequest,
			Authentication authentication) {

		PlaylistDTO playlistDTO = new PlaylistDTO();

		playlistDTO.setAccessability(PlayListAccess.PRIVATE);
		playlistDTO.setPriority(playlistCreationRequest.priority());
		playlistDTO.setSongId(playlistCreationRequest.SongId());
		;
		playlistDTO.setOnwer(userService.getUser(authentication.getName()));
		playlistDTO.setPlaylistName(playlistCreationRequest.playlistName());

		PlaylistDTO createdPlaylistDTO = playlistService.createPlaylist(playlistDTO);

		return new CreatedPlaylistResponse(createdPlaylistDTO.getPlaylistName(), createdPlaylistDTO.getPriority());
	}

	@GetMapping(path = "/public", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<PlaylistResponses> getAllPublicPlayList(Authentication authentication) {

		List<PlaylistDTO> playlistDTOs = playlistService.getPublicPlaylist();
		List<PlaylistResponses> playlistsResponses = new ArrayList<>();

		for (PlaylistDTO playlistDTO : playlistDTOs) {
			Map<String, String> songMap = new HashMap<>();
			if (playlistDTO.getPlaylistSong() != null) {
				for (SongDTO song : playlistDTO.getPlaylistSong()) {
					songMap.put(song.getSongName(), song.getUrl());
				}
			}

			playlistsResponses
					.add(new PlaylistResponses(playlistDTO.getPlaylistName(), playlistDTO.getPriority(), songMap));
		}
		return playlistsResponses;
	}

	@GetMapping(path = "/me", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<PlaylistResponses> getUserPlayList(Authentication authentication) {

		List<PlaylistDTO> playlistDTOs = playlistService.getUserPlaylist(authentication.getName());
		List<PlaylistResponses> playlistsResponses = new ArrayList<>();

		for (PlaylistDTO playlistDTO : playlistDTOs) {
			Map<String, String> songMap = new HashMap<>();
			if (playlistDTO.getPlaylistSong() != null) {
				for (SongDTO song : playlistDTO.getPlaylistSong()) {
					songMap.put(song.getSongName(), song.getUrl());
				}
			}

			playlistsResponses
					.add(new PlaylistResponses(playlistDTO.getPlaylistName(), playlistDTO.getPriority(), songMap));
		}
		return playlistsResponses;
	}

	@PutMapping(path = "/songs", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public PlaylistResponses addSongToPlaylist(@RequestBody PlaylistCreationRequest request) {

		PlaylistDTO playlistDTO = new PlaylistDTO();
		playlistDTO.setPlaylistName(request.playlistName());
		playlistDTO.setSongId(request.SongId());

		PlaylistDTO updatedDTO = playlistService.addSongsToPlaylist(playlistDTO);

		Map<String, String> songMap = new HashMap<>();
		for (SongDTO song : updatedDTO.getPlaylistSong()) {
			songMap.put(song.getSongName(), song.getUrl());
		}

		return new PlaylistResponses(updatedDTO.getPlaylistName(), updatedDTO.getPriority(), songMap);
	}

}
