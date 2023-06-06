package com.game.numberbaseball.repository;

import java.util.List;
import java.util.Optional;

import com.game.numberbaseball.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoryRepository extends JpaRepository<History, Long> {

	@Query("select distinct h from History h left join fetch h.result where h.game.id = ?1")
	Optional<List<History>> findByGameId(@Param("roomId") Long roomId);
}
