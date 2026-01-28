package com.songs.wallah.request;

import java.util.UUID;

public record AddSongToPlaylistRequest(String playlistName, UUID songId) {
}