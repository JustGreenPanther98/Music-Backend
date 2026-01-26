package com.songs.wallah.service;

import java.util.List;
import java.util.UUID;

import com.songs.wallah.DataTransferObject.FavoriteDTO;
import com.songs.wallah.enums.songs.Priority;

public interface FavoriteService {
	List<FavoriteDTO> getFavorites(String email);
	FavoriteDTO setFavoriteSong(String email,UUID songId,Priority priority);
}
