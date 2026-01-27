package com.songs.wallah.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.request.PlaylistCreationRequest;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Playlist APIs")
@RequestMapping("/playlist")
public class PlaylistController {

//	@GetMapping
//	public 
	@PostMapping(consumes=
	{ MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE },produces=
	{ MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public PlayListReponse createPlaylist(@RequestBody PlaylistCreationRequest playlistCreationRequest,Authentication authentication) {
		
	}
}
