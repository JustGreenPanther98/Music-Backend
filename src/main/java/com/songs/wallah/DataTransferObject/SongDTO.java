package com.songs.wallah.DataTransferObject;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.songs.wallah.enums.songs.Category;
import com.songs.wallah.enums.songs.Language;

public class SongDTO {

	private long id;
	private UUID publicId;
	private String songName;
	private String artistName;
	private Category category;
	private Language language;
	private String url;
	private int durationInSeconds;
	private double rating;
	@JsonIgnoreProperties("favSong")
	private List<FavoriteDTO> favoriteSongs;
	@JsonIgnoreProperties("playlistSong")
	private List<PlaylistDTO> playlistSongs;

	public SongDTO() {

	}
	
	public SongDTO(UUID publicId,String songName, String artistName, Category category, Language language, String url,
			int durationInSeconds, double rating) {
		this.publicId=publicId;
		this.songName = songName;
		this.artistName = artistName;
		this.category = category;
		this.language = language;
		this.url = url;
		this.durationInSeconds = durationInSeconds;
		this.rating = rating;
	}

	public SongDTO(String songName, String artistName, Category category, Language language, String url,
			int durationInSeconds, double rating) {
		this.songName = songName;
		this.artistName = artistName;
		this.category = category;
		this.language = language;
		this.url = url;
		this.durationInSeconds = durationInSeconds;
		this.rating = rating;
	}

	public SongDTO(String songName, String artistName, Category category, Language language, String url,
			int durationInSeconds, double rating, List<FavoriteDTO> favoriteSongs,
			List<PlaylistDTO> playlistSongs) {
		super();
		this.songName = songName;
		this.artistName = artistName;
		this.category = category;
		this.language = language;
		this.url = url;
		this.durationInSeconds = durationInSeconds;
		this.rating = rating;
		this.favoriteSongs = favoriteSongs;
		this.playlistSongs = playlistSongs;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UUID getPublicId() {
		return publicId;
	}

	public void setPublicId(UUID publicId) {
		this.publicId = publicId;
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

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDurationInSeconds() {
		return durationInSeconds;
	}

	public void setDurationInSeconds(int durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public List<FavoriteDTO> getFavoriteSongs() {
		return favoriteSongs;
	}

	public void setFavoriteSongs(List<FavoriteDTO> favoriteSongs) {
		this.favoriteSongs = favoriteSongs;
	}

	public List<PlaylistDTO> getPlaylistSongs() {
		return playlistSongs;
	}

	public void setPlaylistSongs(List<PlaylistDTO> playlistSongs) {
		this.playlistSongs = playlistSongs;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}
