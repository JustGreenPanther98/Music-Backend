package com.songs.wallah.service;

import java.util.List;

import com.songs.wallah.DataTransferObject.PlaylistDTO;
import com.songs.wallah.DataTransferObject.UserDTO;

public interface PlaylistService {
	List<PlaylistDTO> getPublicPlaylist();
	List<PlaylistDTO> getUserPlaylist(String email);
	PlaylistDTO createPlaylist(PlaylistDTO playlistDetails);
//	PlaylistDTO deleteSongFromPlaylist(PlaylistDTO updatedPlaylistDetails);
	PlaylistDTO addSongsToPlaylist(PlaylistDTO updatedPlaylistDetails);
}
