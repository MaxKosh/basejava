package com.maxkosh.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiMain {
    public static void main(String[] args) {
        //Java 8 Streams I:
        int[] numbers = {9, 8, 7, 4, 2, 2};
        System.out.println(minValue(numbers));

        //Java 8 Streams II:
        List<Integer> integers = new ArrayList<>();
        for (int i = 2; i < 10; i++) {
            integers.add(i);
        }
        System.out.println(oddOrEven(integers));
    }

    static int minValue(int[] values) {
        final int[] count = {((int) IntStream.of(values).distinct().count() - 1)};
        return IntStream.of(values).distinct().sorted()
                .map(i -> i * (int) Math.pow(10, (count[0]--))).sum();
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream().mapToInt(Integer::intValue).sum() % 2 == 0) {
            return integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        }
        return integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
    }
}
