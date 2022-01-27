package racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;
import racingcar.domain.car.Participants;
import racingcar.domain.racing.RacingResult;
import racingcar.domain.random.MoveGen;
import racingcar.domain.random.NoMoveGen;

class ParticipantsTest {

    final String NAME_LENGTH_ERROR_MESSAGE = "자동차 이름이 5자를 초과합니다.";

    @Test
    public void 이름이_5자_이하인_차량은_Exception발생안함() {
        //given
        String name = "jason";

        //when

        //then
        assertThatCode(() -> Car.from(name)).doesNotThrowAnyException();
    }

    @Test
    public void 이름이_5자를_초과한_차량은_Exception발생() {
        //given
        String name = "jason1";

        //when

        //then
        assertThatCode(() -> Car.from(name)).hasMessageContaining(NAME_LENGTH_ERROR_MESSAGE);
    }

    @Test
    public void 전진조건_만족시_차량의_위치가_일치하는지() {
        //given
        List<Car> cars = new ArrayList<>();
        Car car1 = Car.from("jason");
        Car car2 = Car.from("pobi");
        cars.add(car1);
        cars.add(car2);

        //when
        cars.stream().forEach(car -> car.go(new MoveGen()));

        //then
        assertThat(cars.get(0).getPosition()).isEqualTo(1);
        assertThat(cars.get(1).getPosition()).isEqualTo(1);
    }

    @Test
    public void 전진조건_불만족시_차량의_위치가_변하지_않는지() {
        //given
        List<Car> cars = new ArrayList<>();
        Car car1 = Car.from("jason");
        Car car2 = Car.from("pobi");
        cars.add(car1);
        cars.add(car2);

        //when
        cars.stream().forEach(car -> car.go(new NoMoveGen()));

        //then
        assertThat(cars.get(0).getPosition()).isEqualTo(0);
        assertThat(cars.get(1).getPosition()).isEqualTo(0);
    }

    @DisplayName("RacingResult에서 Participatns로 변환 후 둘은 같은 차량 리스트를 들고 있다.")
    @Test
    public void RacingResult_에서_Participatns_로의_변환() {
        //given
        List<Car> cars = new ArrayList<>();
        Car car1 = Car.from("jason");
        Car car2 = Car.from("pobi");

        car1.go(new MoveGen());
        car1.go(new MoveGen());
        car2.go(new MoveGen());

        cars.add(car1);
        cars.add(car2);

        RacingResult racingResult = RacingResult.getInstance(cars);

        //when
        Participants participants =  new Participants(cars);

        //then
        assertThat(participants.getParticipants()).isEqualTo(racingResult.getResult());
    }
}