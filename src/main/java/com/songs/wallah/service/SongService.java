package com.songs.wallah.service;

import java.util.List;

import com.songs.wallah.DataTransferObject.SongDTO;

public interface SongService {
	SongDTO uploadSong(SongDTO songDTO);
	List<SongDTO> getSongs();
	List<SongDTO> getCategorySongs(String category);
	List<SongDTO> getArtistSongs(String artist);
}
