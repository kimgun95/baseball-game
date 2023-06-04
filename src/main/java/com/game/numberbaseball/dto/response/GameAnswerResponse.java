package com.game.numberbaseball.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GameAnswerResponse {

    private boolean correct;
    private int remainingCount;
    private int strike;
    private int ball;
    private int out;
}
