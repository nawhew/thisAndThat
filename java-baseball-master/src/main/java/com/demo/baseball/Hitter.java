package com.demo.baseball;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Hitter implements Runnable{

    private Scanner scanner;
    private List<Integer> balls;

    public Hitter() {
        this.scanner = new Scanner(System.in);
        this.balls = new ArrayList<>(3);
    }

    public List<Integer> hitTheBalls(String balls) {
        for (String ball : balls.split("")) {
            this.balls.add(Integer.parseInt(ball));
        }
        return this.balls;
    }

    public String checkBalls(String balls) throws Exception{
        boolean matches = Pattern.matches("^[1-9]*$", balls);
        if(!matches) {
            throw new Exception("입력값이 올바르지 않습니다.");
        }
        return  balls;
    }

    @Override
    public void run() {

        while (true) {
            try {
                String input = this.scanner.next();
                this.hitTheBalls(checkBalls(input));
            } catch (Exception e) {
                System.out.println();
            }
        }
    }
}
