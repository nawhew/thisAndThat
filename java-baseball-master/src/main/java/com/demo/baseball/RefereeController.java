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
            this.addDecision(makeDecision(ball));
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

    public void addDecision(Decision decision) {
        if(decision == Decision.STRIKE) {
            this.referee.addStrike();
        } else if(decision == Decision.BALL) {
            this.referee.addBall();;
        }
        this.referee.addCount();
    }

    public String toStringDecision() {
        StringBuilder decision = new StringBuilder();

        if(this.referee.getStrike() > 0) {
            decision.append(this.referee.getStrike()).append(" 스트라이크 ");
        }

        if (this.referee.getBall() > 0) {
            decision.append(this.referee.getBall()).append(" 볼 ");
        }

        if(this.referee.getCount() == 3 && this.referee.getStrike() == 0 && this.referee.getBall() == 0) {
            decision.append("NOTHING");
        }

        return decision.toString();
    }
}
