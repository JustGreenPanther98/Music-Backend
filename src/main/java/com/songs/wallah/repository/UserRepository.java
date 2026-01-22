package com.songs.wallah.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songs.wallah.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>{
	@Query(value="Select * from users where email=:email",nativeQuery=true)
	UserEntity findByEmail(@Param("email") String email);
}
