package com.songs.wallah.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.songs.wallah.DataTransferObject.SongDTO;
import com.songs.wallah.entity.SongEntity;
import com.songs.wallah.repository.SongRepository;
import com.songs.wallah.service.SongService;

@Service
public class SongServiceImpl implements SongService {
	private SongRepository songRepository;

	public SongServiceImpl(SongRepository songRepository) {
		this.songRepository = songRepository;
	}

	@Override
	public SongDTO uploadSong(SongDTO songDTO) {
		if (songDTO == null) {
			throw new RuntimeException("Invalid song credentials");
		}
		ModelMapper modelMapper = new ModelMapper();
		SongEntity song = songRepository.save(modelMapper.map(songDTO, SongEntity.class));
		return modelMapper.map(song, SongDTO.class);
	}

	@Override
	public List<SongDTO> getSongs() {

		Iterable<SongEntity> songs = songRepository.findAll();
		if (songs == null) {
			throw new RuntimeException("Empty");
		}
		List<SongDTO> songDTOs = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		for (SongEntity song : songs) {
			songDTOs.add(modelMapper.map(song, SongDTO.class));
		}
		return songDTOs;
	}

	@Override
	public List<SongDTO> getCategorySongs(String category) {
		List<SongEntity> songs = songRepository.findSongsByCategory(category);
		if (songs == null) {
			throw new RuntimeException("Category doesn't exist");
		}
		List<SongDTO> songDTOs = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		for (SongEntity song : songs) {
			songDTOs.add(modelMapper.map(song, SongDTO.class));
		}
		return songDTOs;
	}

	@Override
	public List<SongDTO> getArtistSongs(String artist) {

		List<SongEntity> songs = songRepository.findSongsByArtistName(artist);
		if (songs == null) {
			throw new RuntimeException("Artist doesn't exist");
		}
		List<SongDTO> songDTOs = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		for (SongEntity song : songs) {
			songDTOs.add(modelMapper.map(song, SongDTO.class));
		}
		return songDTOs;
	}

}
