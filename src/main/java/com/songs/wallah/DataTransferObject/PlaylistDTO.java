package com.songs.wallah.DataTransferObject;

import com.songs.wallah.entity.SongEntity;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.enums.songs.PlayListAccess;

public record PlaylistDTO(long id, String playlistName, PlayListAccess accessability, SongEntity playlistSong,
		UserEntity onwer) 
{

}
