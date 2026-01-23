package com.songs.wallah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songs.wallah.entity.OtpEntity;

import jakarta.transaction.Transactional;

@Repository
public interface OtpRepository extends CrudRepository<OtpEntity,Long>{
	@Modifying
	@Transactional
	@Query(value="delete from otp where expiration_time<CURRENT_TIMESTAMP",nativeQuery=true)
	void deleteOldOtps();
	
	@Query(value="select * from otp where expiration_time>CURRENT_TIMESTAMP AND email=:email order by expiration_time",nativeQuery=true)
	List<OtpEntity> findOtpByEmail(@Param("email") String email);
	
}
