package com.demo.baseball;

public class PitcherTest {

    public static void randomThrowBallsTest() {

        // given
        Pitcher throwBall = new Pitcher();

        // when & then
        throwBall.randomThrowBalls().stream().forEach(System.out::println);
    }

    public static void main(String[] args) {

        PitcherTest.randomThrowBallsTest();
    }
}