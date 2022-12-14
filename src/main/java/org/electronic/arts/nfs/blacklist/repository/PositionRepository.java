package org.electronic.arts.nfs.blacklist.repository;

import java.util.Optional;

import org.electronic.arts.nfs.blacklist.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

	@Modifying
	@Query(nativeQuery = true, value = "CALL INSERT_PROC(:name, :alias, :speciality, :currently_position);")
	public void insertInTheBlacklist(@Param("name") String name, @Param("alias") String alias,
			@Param("speciality") String speciality, @Param("currently_position") int position);

	public Optional<PositionEntity> findByCurrentlyPosition(int position);
}
