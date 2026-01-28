package com.songs.wallah.entity;
import java.util.List;
import java.util.UUID;

import com.songs.wallah.enums.songs.Category;
import com.songs.wallah.enums.songs.Language;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "song_details",uniqueConstraints = {@UniqueConstraint(columnNames = {"url"})})
public class SongEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false)
	private UUID publicId;

	@Column(unique = true, nullable = false, length = 90)
	private String songName;

	@Column(length = 100, nullable = false)
	private String artistName;

	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;

	@Enumerated(EnumType.STRING)
	private Language language;

	@Column(nullable = false,unique=true)
	private String url;

	@Column(nullable = false)
	private int durationInSeconds;

	@DecimalMax(value = "5.0")
	@DecimalMin(value = "0.0")
	@Column(nullable = false)
	private double rating;

	@OneToMany(mappedBy = "favSong", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<FavoriteEntity> favoriteSongs;

	@ManyToMany(mappedBy = "playlistSong", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<PlaylistEntity> playlistSongs;

	@PrePersist
	protected void onCreate() {
		this.publicId = UUID.randomUUID();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artist) {
		this.artistName = artist;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getDuration() {
		return durationInSeconds;
	}

	public void setDuration(int durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UUID getPublicId() {
		return publicId;
	}

	public void setPublicId(UUID publicId) {
		this.publicId = publicId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language langauge) {
		this.language = langauge;
	}

	public List<FavoriteEntity> getFavoriteSongs() {
		return favoriteSongs;
	}

	public void setFavoriteSongs(List<FavoriteEntity> favoriteSongs) {
		this.favoriteSongs = favoriteSongs;
	}

	public List<PlaylistEntity> getPlaylistSongs() {
		return playlistSongs;
	}

	public void setPlaylistSongs(List<PlaylistEntity> playlistSongs) {
		this.playlistSongs = playlistSongs;
	}

}
