package com.songs.wallah.request;

import java.util.List;
import java.util.UUID;

import com.songs.wallah.enums.songs.Priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PlaylistCreationRequest(@NotBlank String playlistName,  Priority priority,
		 @NotNull @NotEmpty UUID SongId) {
}
