package com.songs.wallah.DataTransferObject;

import com.songs.wallah.entity.SongEntity;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.enums.songs.Priority;

public record FavoriteDTO(long id, Priority priority, SongEntity favSong, UserEntity user) {

}
