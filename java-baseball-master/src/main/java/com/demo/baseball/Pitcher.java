package com.demo.baseball;

import java.util.*;

public class Pitcher {

    private static final int DEFAULT_LIST_SIZE = 3;
    private int listSize;
    private String input;
    private List<Integer> numbers;

    public Pitcher() {
        this(DEFAULT_LIST_SIZE);
    }

    private Pitcher(int size) {
        this.numbers = new ArrayList<>(size);
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

    public List<Integer> getNumbers() {
        return numbers;
    }
}
