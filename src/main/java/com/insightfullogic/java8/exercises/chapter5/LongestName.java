package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.maxBy;

public class LongestName {

    public static Artist byReduce(List<Artist> artists) {
        return artists.stream().reduce((a,b)->a.getName().length()>=b.getName().length()?a:b).get();
    }

    public static Artist byCollecting(List<Artist> artists) {
        return artists.stream().collect(//Collector<? super T, A, R> collector
                 maxBy(//return Collector<T, ?, Optional<T>>，need Comparator<? super T> comparator
                         Comparator.comparing((Function<Artist, Integer>)(artist)->artist.getName().length())
                )
        ).orElseThrow(RuntimeException::new);

//        artists.stream().collect(ArrayList::new)
//        return Exercises.replaceThisWithSolution();
    }

}
