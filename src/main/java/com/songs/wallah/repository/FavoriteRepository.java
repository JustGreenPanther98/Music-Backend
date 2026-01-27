package com.songs.wallah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songs.wallah.entity.FavoriteEntity;
import com.songs.wallah.enums.songs.Priority;

@Repository
public interface FavoriteRepository extends CrudRepository<FavoriteEntity,Long>{
	@Query(value="SELECT * from favorite where user_id = :userId order by priority",nativeQuery=true)
	List<FavoriteEntity> getFavoriteSongByPriority(@Param("userId") long userId);
	@Query(value="SELECT * from favorite where user_id = :userId AND priority = :priority",nativeQuery=true)
	List<FavoriteEntity> getFavoriteSongOfPriority(@Param("userId") long userId,@Param ("priority")Priority priority);
}
