package com.songs.wallah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songs.wallah.entity.PlaylistEntity;
import com.songs.wallah.enums.songs.PlayListAccess;

@Repository
public interface PlaylistRepository extends CrudRepository<PlaylistEntity,Long>{
	// Finds all playlists where accessability matches (e.g., PUBLIC)
    @Query(value="select * from playlist where accessability=:accessability",nativeQuery=true)
	List<PlaylistEntity> findAllByAccessability(@Param("accessability")PlayListAccess accessability);

    // Finds all playlists belonging to a specific user ID
    @Query(value="select * from playlist where owner_id=:userId",nativeQuery=true)
    List<PlaylistEntity> findAllByOwnerId(@Param("userId")long userId);
    
    @Query(value="select * from playlist where playlist_name=:playlistName",nativeQuery=true)
    PlaylistEntity findByPlaylistName(@Param("playlistName")String playlistName);
    
    
}
