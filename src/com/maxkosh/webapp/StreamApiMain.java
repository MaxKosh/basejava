package com.maxkosh.webapp;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class StreamApiMain {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3 , 3 , 2, 3};
        System.out.println(minValue(numbers));
    }

    static int minValue(int[] values) {
        IntStream stream = Arrays.stream(values);
        //stream.map(i -> i * 2).forEach(System.out::println);

        //Stream.iterate(values, );
        return stream.sorted().distinct().sum();
    }
}
