package com.songs.wallah.DataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.songs.wallah.entity.SongEntity;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.enums.songs.Priority;

public class FavoriteDTO {
	private long id;
	private Priority priority;
	@JsonIgnoreProperties("favoriteSongs")
	private SongDTO favSong;
	@JsonIgnoreProperties
	private UserDTO user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public SongDTO getFavSong() {
		return favSong;
	}

	public void setFavSong(SongDTO favSong) {
		this.favSong = favSong;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
