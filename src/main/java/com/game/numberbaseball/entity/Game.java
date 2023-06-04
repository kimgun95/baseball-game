package com.game.numberbaseball.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAME_ID")
    private Long id;

    private String answer;
    private int remainingCount;

    private Game(String answer, int remainingCount) {
        this.answer = answer;
        this.remainingCount = remainingCount;
    }

    public static Game makeGame(String answer, int count) {
        return new Game(answer, count);
    }

    public void decreaseRemainingCount() {
        this.remainingCount--;
    }
}
