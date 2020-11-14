package com.demo.baseball;

import java.util.List;

public class RefereeController {

    private final Referee referee;

    private List<Integer> pitchersBall;
    private List<Integer> hittersBall;

    public RefereeController() {
        this.referee = new Referee();
    }

    public List<Integer> getPitchersBall() {
        return pitchersBall;
    }

    public void setPitchersBall(List<Integer> pitchersBall) {
        this.pitchersBall = pitchersBall;
    }

    public List<Integer> getHittersBall() {
        return hittersBall;
    }

    public void setHittersBall(List<Integer> hittersBall) {
        this.hittersBall = hittersBall;
    }

    public void foo() {

        for (int ball : this.hittersBall) {


        }
    }


    public Decision makeDecision(int ball) {
        if (this.pitchersBall.contains(ball)) {
            if (this.pitchersBall.indexOf(ball) == this.referee.getCount()) {
                return Decision.STRIKE;
            }
            return Decision.BALL;
        }
        return Decision.NONE;
    }
}
