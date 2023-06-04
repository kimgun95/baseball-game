package com.game.numberbaseball.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESULT_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "HISTORY_ID")
    private History history;
    private int strike;
    private int ball;
    private int out;

    public Result(History history, int strike, int ball, int out) {
        this.history = history;
        this.strike = strike;
        this.ball = ball;
        this.out = out;
    }
}
