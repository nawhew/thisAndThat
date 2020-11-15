package com.demo.baseball;

import java.util.ArrayList;
import java.util.List;

public class Balls {

    private static final int DEFAULT_LIST_SIZE = 3;
    private int listSize;
    private String input;
    private List<Integer> balls;

    public Balls() {
        this(DEFAULT_LIST_SIZE);
    }

    private Balls(int size) {
        this.balls = new ArrayList<>(size);
        this.listSize = size;
    }

    public static int getDefaultListSize() {
        return DEFAULT_LIST_SIZE;
    }

    public int getListSize() {
        return listSize;
    }

    public String getInput() {
        return input;
    }

    public List<Integer> getBalls() {
        return balls;
    }
}
