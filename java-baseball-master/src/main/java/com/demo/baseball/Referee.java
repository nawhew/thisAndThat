package com.demo.baseball;

public class Referee {

    private int strike;

    private int ball;

    private int count;

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public void addStrike() {
        this.strike++;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public void addBall() {
        this.ball++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount() {
        this.count++;
    }

}
