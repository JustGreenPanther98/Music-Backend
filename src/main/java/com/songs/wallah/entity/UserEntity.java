package com.songs.wallah.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, name = "User_Id", nullable = false)
	private UUID publicId;

	@Column(nullable = false, length = 40)
	private String firstName;

	@Column(nullable = true, length = 35)
	private String middleName;

	@Column(nullable = false, length = 45)
	private String lastName;

	@Max(value = 150, message = "Invalid age")
	@Min(value = 0, message = "Invalid age")
	@Column(nullable = false)
	private int age;

	@Column(unique = true, nullable = false, length = 70)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword;

	private boolean emailVerification = false;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<FavoriteEntity> favorites;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<PlaylistEntity> playlist;

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
