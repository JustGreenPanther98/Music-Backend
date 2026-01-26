package com.songs.wallah.response;

import com.songs.wallah.enums.songs.Category;
import com.songs.wallah.enums.songs.Priority;

public record FavoriteResponse(String songName,String artistName,Category category,String url,Priority priority) {

}
