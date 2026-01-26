package com.songs.wallah.request;

import java.util.UUID;

import com.songs.wallah.enums.songs.Category;
import com.songs.wallah.enums.songs.Language;

import jakarta.validation.constraints.NotBlank;

public record UpdateSongRequest(@NotBlank UUID publicId, @NotBlank String updatedSongName,
		@NotBlank String updatedArtistName, @NotBlank Category updatedCategory, @NotBlank Language updatedLanguage,
		@NotBlank String updatedUrl, @NotBlank int updatedDurationInSeconds, @NotBlank double updatedRating) {

}
