package com.maxkosh.webapp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamApiMain {
    public static void main(String[] args) {
        //Java 8 Streams I:
        int[] numbers = {3, 7, 4, 4, 6, 9, 3};
        System.out.println(minValue(numbers));

        //Java 8 Streams II:
        List<Integer> integers = new ArrayList<>();
        for (int i = 2; i < 10; i++) {
            integers.add(i);
        }
        System.out.println(oddOrEven(integers));
    }

    static int minValue(int[] values) {
        return IntStream.of(values).sorted().distinct().reduce(0, (x, y) -> (x * 10) + y);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> oddOrEven =
                integers.stream().collect(Collectors.partitioningBy(s -> s % 2 == 0));
        return oddOrEven.get(integers.stream().mapToInt(Integer::intValue).sum() % 2 == 0);
    }
}
