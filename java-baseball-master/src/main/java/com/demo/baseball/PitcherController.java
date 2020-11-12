package com.demo.baseball;

import java.util.List;
import java.util.Random;

public class PitcherController {

    private static Pitcher pitcher = new Pitcher();

    private final Random random = new Random();

    public static Pitcher getPitcher() {
        return pitcher;
    }

    public static void setPitcher(Pitcher pitcher) {
        PitcherController.pitcher = pitcher;
    }

    public List<Integer> randomThrowBalls() {
        for (int i = 0; i < PitcherController.getPitcher().getListSize(); i++) {
            PitcherController.getPitcher().getNumbers().add(this.randomThrowBall());
        }

        return PitcherController.getPitcher().getNumbers();
    }

    public int randomThrowBall() {
        int number = 0;

        while (true) {
            number = this.random.nextInt(10);
            if (number != 0 && !PitcherController.getPitcher().getNumbers().contains(number)) {
                return number;
            }
        }
    }
}
