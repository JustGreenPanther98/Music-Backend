package com.songs.wallah.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.songs.wallah.DataTransferObject.PlaylistDTO;
import com.songs.wallah.DataTransferObject.UserDTO;
import com.songs.wallah.entity.PlaylistEntity;
import com.songs.wallah.entity.SongEntity;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.enums.songs.PlayListAccess;
import com.songs.wallah.repository.PlaylistRepository;
import com.songs.wallah.repository.SongRepository;
import com.songs.wallah.repository.UserRepository;
import com.songs.wallah.service.PlaylistService;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	private final PlaylistRepository playlistRepository;
	private final SongRepository songRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public PlaylistServiceImpl(PlaylistRepository playlistRepository, SongRepository songRepo,
			ModelMapper modelMapper,UserRepository userRepository) {
		this.songRepository = songRepo;
		this.playlistRepository = playlistRepository;
		this.modelMapper = modelMapper;
		this.userRepository=userRepository;
	}

	@Override
	@Transactional
	public PlaylistDTO createPlaylist(PlaylistDTO playlistDetails) {
		if (playlistDetails.getPlaylistName().length() < 3) {
			throw new RuntimeException("Playlist name is too short!");
		}

		SongEntity song = songRepository.findSongByPublicId(playlistDetails.getSongId());
		if (song == null) {
			throw new RuntimeException("Song not found!");
		}

		PlaylistEntity playlist = new PlaylistEntity();
		playlist.setPlaylistName(playlistDetails.getPlaylistName());
		playlist.setAccessability(playlistDetails.getAccessability());
		playlist.setPriority(playlistDetails.getPriority());
		playlist.setOwner(modelMapper.map(playlistDetails.getOnwer(), UserEntity.class));

		List<SongEntity> songs = new ArrayList<>();
		songs.add(song);
		playlist.setPlaylistSong(songs);

		PlaylistEntity saved = playlistRepository.save(playlist);
		return modelMapper.map(saved, PlaylistDTO.class);
	}

	@Override
	public List<PlaylistDTO> getPublicPlaylist() {
		Iterable<PlaylistEntity> allPlaylists = playlistRepository.findAllByAccessability(PlayListAccess.PUBLIC);
		List<PlaylistDTO> publicPlaylists = new ArrayList<>();

		for (PlaylistEntity playlist : allPlaylists) {
				publicPlaylists.add(modelMapper.map(playlist, PlaylistDTO.class));
		}
		return publicPlaylists;
	}

	@Override
	public List<PlaylistDTO> getUserPlaylist(String email) {
		Iterable<PlaylistEntity> allPlaylists = playlistRepository.findAllByOwnerId(userRepository.findByEmail(email).getId());
		List<PlaylistDTO> userPlaylists = new ArrayList<>();

		for (PlaylistEntity playlist : allPlaylists) {
				userPlaylists.add(modelMapper.map(playlist, PlaylistDTO.class));
		}
		return userPlaylists;
	}

	@Override
	@Transactional
	public PlaylistDTO addSongsToPlaylist(PlaylistDTO updatedPlaylistDetails) {
		PlaylistEntity playlist = playlistRepository.findByPlaylistName(updatedPlaylistDetails.getPlaylistName());
		if(playlist==null) {
			throw new RuntimeException("Playlist doesn't exist");
		}
		SongEntity songToAdd = songRepository.findSongByPublicId(updatedPlaylistDetails.getSongId());
		if (songToAdd == null) {
			throw new RuntimeException("Song not found");
		}

		// Standard list addition
		playlist.getPlaylistSong().add(songToAdd);

		PlaylistEntity saved = playlistRepository.save(playlist);
		return modelMapper.map(saved, PlaylistDTO.class);
	}
}