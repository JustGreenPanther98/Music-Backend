package com.songs.wallah.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.songs.wallah.DataTransferObject.FavoriteDTO;
import com.songs.wallah.entity.FavoriteEntity;
import com.songs.wallah.entity.SongEntity;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.enums.songs.Priority;
import com.songs.wallah.repository.FavoriteRepository;
import com.songs.wallah.repository.SongRepository;
import com.songs.wallah.repository.UserRepository;
import com.songs.wallah.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	private FavoriteRepository favoriteRepository;
	private UserRepository userRepository;
	private SongRepository songRepository;

	public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserRepository userRepository,SongRepository songRepository) {
		this.songRepository=songRepository;
		this.favoriteRepository = favoriteRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<FavoriteDTO> getFavorites(String email) {
		
		UserEntity user = userRepository.findByEmail(email);
		List<FavoriteDTO> favoriteDTOs = new ArrayList<>();
		ModelMapper mapper = new ModelMapper();
		for(FavoriteEntity favorite : user.getFavorites()) {
			favoriteDTOs.add(mapper.map(favorite, FavoriteDTO.class));
		}
		
		return favoriteDTOs;
	}

	@Override
	public FavoriteDTO setFavoriteSong(String email ,UUID songId,Priority priority) {
		if(songId==null || songId.toString().isEmpty()) {
			throw new RuntimeException("Invalid");
		}
		SongEntity song = songRepository.findSongByPublicId(songId);
		if(song==null) {
			throw new RuntimeException("Song doesn't exist");
		}
		FavoriteEntity favorite = new FavoriteEntity();
		ModelMapper mapper = new ModelMapper();
		favorite.setFavSong(song);
		favorite.setPriority(priority);
		favorite.setUser(userRepository.findByEmail(email));
		return mapper.map(favoriteRepository.save(favorite),FavoriteDTO.class);
	}

}
