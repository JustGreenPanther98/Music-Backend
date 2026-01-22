package com.songs.wallah.DataTransferObject;

import java.util.List;
import java.util.UUID;

import com.songs.wallah.entity.FavoriteEntity;
import com.songs.wallah.entity.PlaylistEntity;
import com.songs.wallah.enums.songs.Category;
import com.songs.wallah.enums.songs.Langauge;

public record SongDTO(long id, UUID publicId, String songName, String artistName, Category category, Langauge langauge,
		String url, int durationInSeconds, double rating, List<FavoriteEntity> favoriteSongs,
		List<PlaylistEntity> playlistSongs) {

}
