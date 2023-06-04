package com.game.numberbaseball.repository;

import com.game.numberbaseball.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
