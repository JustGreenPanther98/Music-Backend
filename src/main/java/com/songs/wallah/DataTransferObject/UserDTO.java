package com.songs.wallah.DataTransferObject;

import java.util.List;
import java.util.UUID;

import com.songs.wallah.entity.FavoriteEntity;
import com.songs.wallah.entity.PlaylistEntity;

public class UserDTO {

	private long id;
	private UUID publicId;
	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	private String email;
	private String encryptedPassword;
	private boolean emailVerification=false;
	private List<FavoriteEntity> favorites;
	private List<PlaylistEntity> playlist;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public boolean isEmailVerification() {
		return emailVerification;
	}

	public void setEmailVerification(boolean emailVerification) {
		this.emailVerification = emailVerification;
	}

	public List<FavoriteEntity> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<FavoriteEntity> favorites) {
		this.favorites = favorites;
	}

	public List<PlaylistEntity> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<PlaylistEntity> playlist) {
		this.playlist = playlist;
	}

}
