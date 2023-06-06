package com.game.numberbaseball;

import com.game.numberbaseball.dto.request.GameAnswerRequest;
import com.game.numberbaseball.dto.response.GameAnswerResponse;
import com.game.numberbaseball.dto.response.GameResultResponse;
import com.game.numberbaseball.dto.response.GameStartResponse;
import com.game.numberbaseball.entity.Game;
import com.game.numberbaseball.entity.History;
import com.game.numberbaseball.entity.Result;
import com.game.numberbaseball.repository.GameRepository;
import com.game.numberbaseball.repository.HistoryRepository;
import com.game.numberbaseball.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
@Transactional
@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final HistoryRepository historyRepository;
    private final ResultRepository resultRepository;
    private final int TOTAL_GAME_ANSWER_COUNT = 10;
    private final int RANDOM_NUMBER_LENGTH = 3;

    public GameStartResponse makeGameRoom() {
        Game game = Game.makeGame(makeGameAnswer(), TOTAL_GAME_ANSWER_COUNT);
        gameRepository.save(game);
        return new GameStartResponse(game.getId());
    }

    public GameAnswerResponse checkGameAnswer(final Long roomId, final GameAnswerRequest request) {
        Game game = findByIdOrElseThrow(roomId);
        return verifyAnswer(game, request.getAnswer(), game.getAnswer());
    }

    public GameResultResponse checkGameResult(final Long roomId) {
        Game game = findByIdOrElseThrow(roomId);
        List<History> histories = findByGameIdOrElseThrow(roomId);
        return new GameResultResponse(game.getRemainingCount(), histories.size());
    }

    private Game findByIdOrElseThrow(final Long roomId) {
        return gameRepository.findById(roomId).orElseThrow(
                () -> new NullPointerException());
    }

    private List<History> findByGameIdOrElseThrow(final Long roomId) {
        return historyRepository.findByGameId(roomId).orElseThrow(
            () -> new NullPointerException());
    }

    private GameAnswerResponse verifyAnswer(final Game game, final String prediction, final String answer) {
        int strike = 0;
        int ball = 0;
        int out = 0;
        for (int i = 0; i < RANDOM_NUMBER_LENGTH; i++) {
            char ch = prediction.charAt(i);
            for (int j = 0; j < RANDOM_NUMBER_LENGTH; j++) {
                if (ch == answer.charAt(j)) {
                    if (i == j) {
                        strike++;
                    } else {
                        ball++;
                    }
                    break;
                }
                if (j == RANDOM_NUMBER_LENGTH - 1) {
                    out++;
                }
            }
        }

        boolean correct = false;
        if (strike == 3) {
            correct = true;
        }
        game.decreaseRemainingCount();
        saveHistoryAndResult(game, prediction, strike, ball, out);
        return new GameAnswerResponse(correct, game.getRemainingCount(), strike, ball, out);
    }

    private void saveHistoryAndResult(final Game game, final String prediction, final int strike, final int ball, final int out) {
        History history = new History(game, prediction);
        Result result = new Result(history, strike, ball, out);
        historyRepository.save(history);
        resultRepository.save(result);
    }

    private String makeGameAnswer() {
        int count = RANDOM_NUMBER_LENGTH;
        int[] randomNum = new int[count];
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            randomNum[i] = r.nextInt(9) + 1;
            for (int j = 0; j < i; j++) {
                if (randomNum[i] == randomNum[j]) {
                    i--;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < count; i++) {
            answer.append(randomNum[i]);
        }
        return answer.toString();
    }
}
