package com.demo.baseball;

import java.util.Arrays;

public class JyCodingTest {

    public static void getMaxElement(int[] a, int[] lotations) {

        int[] result = new int[lotations.length];
        Arrays.parallelSort(a);
        int maxElementIndex = a[a.length - 1] - 1;
        int maxIndex = a.length - 1;
        int findex = 0;

        for (int lotate : lotations) {
            int processIndex = maxElementIndex;
            System.out.println("processIndex start : " + processIndex);
            System.out.println("maxIndex : " + maxIndex);
            for (int i = 0; i < lotate; i++) {
                if(processIndex > 0 ) {
                    processIndex--;
                } else if(processIndex == 0) {
                    processIndex = maxIndex;
                }
            }

            result[findex++] = processIndex;
        }

        Arrays.stream(result).forEach(System.out::println);

    }

    public static void main(String[] args) {

        getMaxElement(new int[]{1,2,3}, new int[]{1,2,3,4});

    }

}
