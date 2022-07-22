package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 2, 5, 4};
        System.out.println(minValue(nums));

        List<Integer> integers = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.println(oddOrEven(integers));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sumRemainder = integers.stream().mapToInt(Integer::intValue).sum() % 2;
        return integers.stream()
                .filter(el1 -> el1 % 2 != sumRemainder)
                .collect(Collectors.toList());
    }
}
