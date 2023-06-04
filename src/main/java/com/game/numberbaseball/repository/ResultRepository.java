package com.game.numberbaseball.repository;

import com.game.numberbaseball.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
