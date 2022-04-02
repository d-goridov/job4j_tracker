package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;

public class FilterNegativeNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, -3, 20, 0, -32, 4);
        List<Integer> positive = numbers.stream().filter(i -> i > 0).toList();
        positive.forEach(System.out::println);
    }
}
