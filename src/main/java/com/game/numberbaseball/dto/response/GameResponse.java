package com.game.numberbaseball.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GameResponse<T> {
    private boolean success;
    private T data;

    public static <T> GameResponse<T> setSuccess(T data){
        return new GameResponse<>(true, data);
    }
}
