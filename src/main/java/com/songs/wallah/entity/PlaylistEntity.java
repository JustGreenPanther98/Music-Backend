package com.songs.wallah.entity;

import com.songs.wallah.enums.songs.PlayListAccess;
import com.songs.wallah.enums.songs.Priority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "playlist")
public class PlaylistEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 20)
	private String playlistName;

	@Enumerated(EnumType.STRING)
	private Priority priority;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PlayListAccess accessability;

	@ManyToOne
	private SongEntity playlistSong;

	@ManyToOne
	private UserEntity owner;

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

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

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public SongEntity getPlaylistSong() {
		return playlistSong;
	}

	public void setPlaylistSong(SongEntity playlistSong) {
		this.playlistSong = playlistSong;
	}

}
