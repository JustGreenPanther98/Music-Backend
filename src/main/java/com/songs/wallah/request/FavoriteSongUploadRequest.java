package com.songs.wallah.request;

import java.util.UUID;

import com.songs.wallah.enums.songs.Priority;

import jakarta.validation.constraints.NotBlank;

public record FavoriteSongUploadRequest(@NotBlank UUID songId,Priority priority) {

}
