package com.demo.baseball;

import java.util.*;

public class Pitcher {

    private static final int DEFAULT_LIST_SIZE = 3;
    private int listSize;
    private String input;
    private List<Integer> balls;

    public Pitcher() {
        this(DEFAULT_LIST_SIZE);
    }

    private Pitcher(int size) {
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
