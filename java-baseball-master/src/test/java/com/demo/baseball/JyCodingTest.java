package com.demo.baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static void getMaxElementForList(List<Integer> a, List<Integer> lotations) {

        int[] result = new int[lotations.size()];
        int maxElementIndex = a.indexOf(Collections.max(a));
        int maxIndex = a.size() - 1;
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

//        getMaxElement(new int[]{1,2,3}, new int[]{1,2,3,4});

        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);

        List<Integer> lotate = new ArrayList<>();
        lotate.add(1);
        lotate.add(2);
        lotate.add(3);
        lotate.add(4);
        getMaxElementForList(a, lotate);

    }

}
