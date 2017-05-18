package com.insightfullogic.java8.exercises.chapter6;

import java.util.List;

public class BuggyReduce {

    public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.stream().parallel()
                                  .reduce(1, (acc, x) -> x * acc)*5;
    }

}
