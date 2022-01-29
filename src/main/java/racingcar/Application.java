package racingcar;

import racingcar.controller.RacingController;
import racingcar.view.ResultView;
import racingcar.view.UserConsole;

public class Application {

    public static void main(String[] args) {
        RacingController racingController = RacingController.getInstance(UserConsole.names(), UserConsole.turn().getValue());
        racingController.start();

        ResultView.printRacingResult(racingController.getRacingProcess());
        ResultView.printWinner(racingController.getWinner());
    }
}