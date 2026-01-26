package com.songs.wallah.request;

import com.songs.wallah.enums.songs.Category;
import com.songs.wallah.enums.songs.Language;

import jakarta.validation.constraints.NotBlank;

public record SongUploadRequest(@NotBlank String songName, @NotBlank String artistName, @NotBlank Category category,
		@NotBlank Language language, @NotBlank String url, int durationInSeconds, @NotBlank double rating) {

}
