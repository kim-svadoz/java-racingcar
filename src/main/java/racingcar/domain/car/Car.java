package racingcar.domain.car;

import racingcar.domain.random.RandomStrategy;

public class Car {

    private static final int INITIAL_POSITION = 0;
    private static final int THRESHOLD_TO_MOVE = 4;
    private static final int MOVE_ONE_STEP = 1;

    private final Name name;
    public int position;

    private Car(String name) {
        this.name = new Name(name);
        this.position = INITIAL_POSITION;
    }

    public Car(String name, int position) {
        this.name = new Name(name);
        this.position = position;
    }

    public static Car from(String name) {
        return new Car(name);
    }

    public static Car of(String name, int position) {
        return new Car(name, position);
    }

    public void go(RandomStrategy randomStrategy) {
        if (randomStrategy.get() > THRESHOLD_TO_MOVE) {
            this.position += MOVE_ONE_STEP;
        }
    }

    public boolean comparePosition(Car o) {
        return position == o.position;
    }

    public String getName() {
        return this.name.getName();
    }

    public int getPosition() {
        return this.position;
    }
}
