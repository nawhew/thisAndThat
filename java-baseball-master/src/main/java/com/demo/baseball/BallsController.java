package com.demo.baseball;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class BallsController {

    private final Balls balls;

    private final Random random = new Random();

    public BallsController() {
        this(new Balls());
    }

    public BallsController(Balls balls) {
        this.balls = balls;
    }

    public List<Integer> randomThrowBalls() {
        for (int i = 0; i < this.balls.getListSize(); i++) {
            this.balls.getBalls().add(this.randomThrowBall());
        }

        return this.balls.getBalls();
    }

    public int randomThrowBall() {
        int number = 0;

        while (true) {
            number = this.random.nextInt(10);
            if (number != 0 && !this.balls.getBalls().contains(number)) {
                return number;
            }
        }
    }

    public List<Integer> hitTheBalls(String balls) {
        for (String ball : balls.split("")) {
            this.balls.getBalls().add(Integer.parseInt(ball));
        }
        return this.balls.getBalls();
    }

    public String checkBalls(String balls) throws Exception{
        boolean matches = Pattern.matches("^[1-9]*$", balls);
        if(!matches) {
            throw new Exception("입력값이 올바르지 않습니다.");
        }
        return balls;
    }

}
