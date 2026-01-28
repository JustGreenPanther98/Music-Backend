package com.songs.wallah.service;

import java.util.List;
import java.util.UUID;

import com.songs.wallah.DataTransferObject.SongDTO;

public interface SongService {
	
	SongDTO uploadSong(SongDTO songDTO);

	List<SongDTO> uploadSong(List<SongDTO> songDTO);
	
	List<SongDTO> getSongs();
	
	List<SongDTO> getCategorySongs(String category);

	List<SongDTO> getArtistSongs(String artist);

	List<SongDTO> getLanguageSongs(String language);

	SongDTO updateSong(SongDTO songDTO);
	
	SongDTO getBySongId(UUID id);
}
