package com.demo.baseball;

import java.util.*;

public class ThrowBall {

    private static final int DEFAULT_LIST_SIZE = 3;
    private int listSize;

    private final Random random = new Random();
    private String input;
    private List<Integer> numbers;

    public ThrowBall() {
        this(DEFAULT_LIST_SIZE);
    }

    private ThrowBall(int size) {
        this.numbers = new ArrayList<>(size);
        this.listSize = size;
    }

    public List<Integer> randomThrowBalls() {
        for (int i = 0; i < this.listSize; i++) {
            this.numbers.add(this.randomThrowBall());
        }

        return this.numbers;
    }

    public int randomThrowBall() {
        int number = 0;

        while (true) {
            number = this.random.nextInt(10);
            if (number != 0 && !this.numbers.contains(number)) {
                return number;
            }
        }
    }


    public static void main(String[] args) {
        ThrowBall throwBall = new ThrowBall();

        throwBall.randomThrowBalls().stream().forEach(System.out::println);
    }

}
