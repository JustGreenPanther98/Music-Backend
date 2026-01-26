package com.songs.wallah.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.songs.wallah.entity.FavoriteEntity;

@Repository
public interface FavoriteRepository extends CrudRepository<FavoriteEntity,Long>{
	
}
