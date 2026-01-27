package com.songs.wallah.request;

import com.songs.wallah.entity.SongEntity;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.enums.songs.PlayListAccess;
import com.songs.wallah.enums.songs.Priority;

import jakarta.validation.constraints.NotBlank;

public record PlaylistCreationRequest(@NotBlank String playlistName, @NotBlank Priority priority,
		@NotBlank PlayListAccess accessability, @NotBlank SongEntity playlistSong, @NotBlank UserEntity owner) {
}
