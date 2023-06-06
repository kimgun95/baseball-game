package com.game.numberbaseball;

import com.game.numberbaseball.dto.request.GameAnswerRequest;
import com.game.numberbaseball.dto.response.GameAnswerResponse;
import com.game.numberbaseball.dto.response.GameResponse;
import com.game.numberbaseball.dto.response.GameStartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GameController {

    private final GameService gameService;

    @PostMapping("/game/start")
    public ResponseEntity<GameResponse<GameStartResponse>> gameStart() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(GameResponse.setSuccess(gameService.makeGameRoom()));
    }

    @PostMapping("/game/{room-id}/answer")
    public ResponseEntity<GameResponse<GameAnswerResponse>> gameAnswer(
        @PathVariable("room-id") final Long roomId,
        @RequestBody final GameAnswerRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(GameResponse.setSuccess(gameService.checkGameAnswer(roomId, request)));
    }

    @GetMapping("/game/{room-id}")
    public ResponseEntity<GameResponse> gameResult(
        @PathVariable("room-id") final Long roomId) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(GameResponse.setSuccess(gameService.checkGameResult(roomId)));
    }
}
