package com.demo.baseball;

import static org.junit.jupiter.api.Assertions.*;

public class ThrowBallTest {

    public static void randomThrowBallsTest() {

        // given
        ThrowBall throwBall = new ThrowBall();

        // when & then
        throwBall.randomThrowBalls().stream().forEach(System.out::println);
    }

    public static void main(String[] args) {

        ThrowBallTest.randomThrowBallsTest();
    }
}