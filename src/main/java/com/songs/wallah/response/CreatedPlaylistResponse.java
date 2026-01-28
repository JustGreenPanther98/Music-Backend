package com.songs.wallah.response;

import com.songs.wallah.enums.songs.Priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatedPlaylistResponse(@NotBlank String playlistName, @NotNull Priority priority) {

}
