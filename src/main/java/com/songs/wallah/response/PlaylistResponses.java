package com.songs.wallah.response;

import java.util.Map;

import com.songs.wallah.enums.songs.Priority;

public record PlaylistResponses (String playlistName,Priority priority, Map<String,String> songAndUrl){

}
