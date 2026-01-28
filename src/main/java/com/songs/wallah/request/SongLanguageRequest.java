package com.songs.wallah.request;

import com.songs.wallah.enums.songs.Language;

import jakarta.validation.constraints.NotBlank;

public record SongLanguageRequest(@NotBlank Language langauge) {

}
