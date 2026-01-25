package com.songs.wallah.request;

import jakarta.validation.constraints.NotBlank;

public record SongArtistRequest (@NotBlank String Artist){

}
