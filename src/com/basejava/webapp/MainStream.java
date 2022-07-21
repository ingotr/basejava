package com.basejava.webapp;

import java.util.*;
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
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        Predicate<Integer> isEven = el -> el % 2 == 0;
        Predicate<Integer> notEven = el -> el % 2 != 0;
        if (sum % 2 == 0) {
            return getList(integers, isEven);
        }
        return getList(integers, notEven);
    }

    private static List<Integer> getList(List<Integer> integers, Predicate<Integer> isEven) {
        return integers.stream()
                .filter(isEven)
                .collect(Collectors.toList());
    }
}
