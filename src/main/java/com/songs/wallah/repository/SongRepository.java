package com.songs.wallah.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songs.wallah.entity.SongEntity;

@Repository
public interface SongRepository extends CrudRepository<SongEntity, Long> {
	@Query(value = "SELECT * from song_details WHERE category = :category", nativeQuery = true)
	List<SongEntity> findSongsByCategory(@Param("category") String category);

	@Query(value = "SELECT * from song_details WHERE artist_name = :artistName", nativeQuery = true)
	List<SongEntity> findSongsByArtistName(@Param("artistName") String category);

	@Query(value = "select * from song_details WHERE language = :language", nativeQuery = true)
	List<SongEntity> findSongsByLanguage(@Param("language") String language);

	@Query(value = "select * from song_details WHERE public_id = :id", nativeQuery = true)
	SongEntity findSongByPublicId(@Param("id") UUID publicId);
}
