package com.songs.wallah.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songs.wallah.DataTransferObject.FavoriteDTO;
import com.songs.wallah.request.FavoriteSongUploadRequest;
import com.songs.wallah.response.FavoriteResponse;
import com.songs.wallah.service.FavoriteService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/favorites")
@Tag(name = "Find/Create/Remove favorite song")
public class FavoriteController {

//	private UserService userService;
//	private SongService songService;
	private FavoriteService favoriteService;

	public FavoriteController(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
//		this.userService = userService;
//		this.songService = songService;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<FavoriteResponse> getFavoriteSongs(Authentication authentication) {

		List<FavoriteResponse> favoriteResponses = new ArrayList<>();
		List<FavoriteDTO> favoriteDTOs = favoriteService.getFavorites(authentication.getName());
		for (FavoriteDTO favoriteDTO : favoriteDTOs) {
			favoriteResponses.add(new FavoriteResponse(favoriteDTO.getFavSong().getSongName(),
					favoriteDTO.getFavSong().getArtistName(), favoriteDTO.getFavSong().getCategory(),
					favoriteDTO.getFavSong().getUrl(), favoriteDTO.getPriority()));
		}
		return favoriteResponses;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public FavoriteResponse setFavoriteSongs(FavoriteSongUploadRequest favoriteSongRequest,
			Authentication authentication) {

		FavoriteDTO favoriteDTO = favoriteService.setFavoriteSong(authentication.getName(),
				favoriteSongRequest.songId(), favoriteSongRequest.priority());
		FavoriteResponse favoriteSongResponse = new FavoriteResponse(favoriteDTO.getFavSong().getSongName(),
				favoriteDTO.getFavSong().getArtistName(), favoriteDTO.getFavSong().getCategory(),
				favoriteDTO.getFavSong().getUrl(), favoriteDTO.getPriority());
		return favoriteSongResponse;
	}

}