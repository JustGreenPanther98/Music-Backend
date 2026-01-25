package com.songs.wallah.response;

import java.util.UUID;

import com.songs.wallah.enums.songs.Category;
import com.songs.wallah.enums.songs.Language;

public record SongResponse(UUID publicId,String songName,String artistName, Category category, Language language,int durationInSeconds,double rating) {

}
