package com.insightfullogic.java8.exercises.chapter3;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Advanced Exercises Question 2
 */
public class MapUsingReduce {

    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce((Stream<O>)Stream.empty(),//identity
                (a,b)->{//accumulator
                return Stream.concat(a,Stream.of(mapper.apply(b)));
            },(a,b)->{//combiner
            return Stream.concat(a,b);
        })
        .collect(Collectors.toList());
    }

}
