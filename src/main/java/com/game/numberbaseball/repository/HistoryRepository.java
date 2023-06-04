package com.game.numberbaseball.repository;

import com.game.numberbaseball.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
