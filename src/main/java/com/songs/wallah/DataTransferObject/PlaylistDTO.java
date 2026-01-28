package com.songs.wallah.DataTransferObject;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.songs.wallah.enums.songs.PlayListAccess;
import com.songs.wallah.enums.songs.Priority;

public class PlaylistDTO {
	private long id;
	private String playlistName;
	private Priority priority;
	private PlayListAccess accessability;
	@JsonIgnoreProperties("playlistSong")
	private List<SongDTO> playlistSong;
	private UUID songId;
	@JsonIgnoreProperties
	private UserDTO onwer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public PlayListAccess getAccessability() {
		return accessability;
	}

	public void setAccessability(PlayListAccess accessability) {
		this.accessability = accessability;
	}

	public List<SongDTO> getPlaylistSong() {
		return playlistSong;
	}

	public void setPlaylistSong(List<SongDTO> playlistSong) {
		this.playlistSong = playlistSong;
	}

	public UserDTO getOnwer() {
		return onwer;
	}

	public void setOnwer(UserDTO onwer) {
		this.onwer = onwer;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public UUID getSongId() {
		return songId;
	}

	public void setSongId(UUID songId) {
		this.songId = songId;
	}

}
