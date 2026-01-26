package com.songs.wallah.DataTransferObject;

import com.songs.wallah.entity.SongEntity;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.enums.songs.Priority;

public class FavoriteDTO {
	private long id;
	private Priority priority;
	private SongEntity favSong;
	private UserEntity user;

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

	public SongEntity getFavSong() {
		return favSong;
	}

	public void setFavSong(SongEntity favSong) {
		this.favSong = favSong;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
