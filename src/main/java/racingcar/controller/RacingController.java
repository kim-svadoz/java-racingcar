package racingcar.controller;

import java.util.List;
import racingcar.domain.car.Participants;
import racingcar.domain.car.Turn;
import racingcar.dto.ParticipantsRequestDto;
import racingcar.dto.TurnRequestDto;
import racingcar.domain.racing.RacingGame;
import racingcar.domain.random.RandomGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    public void start() {
        RacingGame racingGame = makeRacingGame();

        List<Participants> racingResult = racingGame.start();

        printRacingRecord(racingResult);
        printWinner(racingGame.findWinners(racingResult));
    }

    private RacingGame makeRacingGame() {
        try {
            return new RacingGame(makeParticipants(), makeTurnCount(), new RandomGenerator());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return makeRacingGame();
        }
    }

    private Participants makeParticipants() {
        ParticipantsRequestDto participantsRequestDto = InputView.setNames();
        return participantsRequestDto.toEntity();
    }

    private Turn makeTurnCount() {
        TurnRequestDto turnRequestDto = InputView.setTurnCount();
        return turnRequestDto.toEntity();
    }

    private void printRacingRecord(final List<Participants> racingResult) {
        OutputView.printRacingResult(racingResult);
    }

    private void printWinner(final String winners) {
        OutputView.printWinner(winners);
    }
}
