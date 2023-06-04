package com.game.numberbaseball.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private Long id;

    @JoinColumn(name = "GAME_ID")
    @ManyToOne
    private Game game;
    private String answer;

    @OneToOne(mappedBy = "history")
    private Result result;

    public History(Game game, String answer) {
        this.game = game;
        this.answer = answer;
    }
}
