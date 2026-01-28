package com.songs.wallah.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.songs.wallah.DataTransferObject.SongDTO;
import com.songs.wallah.entity.SongEntity;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.repository.SongRepository;
import com.songs.wallah.repository.UserRepository;
import com.songs.wallah.service.SongService;

@Service
public class SongServiceImpl implements SongService {
	private SongRepository songRepository;
	private UserRepository userRepository;

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
	public List<SongDTO> uploadSong(List<SongDTO> songDTOs) {
		
		if (songDTOs == null) {
			throw new RuntimeException("Invalid song credentials");
		}
		List<SongDTO> addedSongDTOs = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		for(SongDTO songDTO : songDTOs) {
			SongEntity song = songRepository.save(modelMapper.map(songDTO, SongEntity.class));
			addedSongDTOs.add(modelMapper.map(song, SongDTO.class));
		}
		return addedSongDTOs;
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

	@Override
	public List<SongDTO> getLanguageSongs(String language) {
		List<SongEntity> songs = songRepository.findSongsByLanguage(language);
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

	@Override
	public SongDTO updateSong(SongDTO updatedUserDTO) {
		
		SongEntity songEntity = songRepository.findSongByPublicId(updatedUserDTO.getPublicId());
		songEntity.setArtistName(updatedUserDTO.getArtistName());
		songEntity.setSongName(updatedUserDTO.getSongName());
		songEntity.setCategory(updatedUserDTO.getCategory());
		songEntity.setLanguage(updatedUserDTO.getLanguage());
		songEntity.setDuration(updatedUserDTO.getDurationInSeconds());
		songEntity.setUrl(updatedUserDTO.getUrl());
		songEntity.setRating(updatedUserDTO.getRating());
		SongEntity updatedSong = songRepository.save(songEntity);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(updatedSong, SongDTO.class);
	}

	@Override
	public SongDTO getBySongId(UUID id) {
		SongEntity song = songRepository.findSongByPublicId(id);
		if(song==null) {
			throw new RuntimeException(id +" doesn't exist");
		}
		ModelMapper mapper = new ModelMapper();
		return mapper.map(song, SongDTO.class);
	}

}
