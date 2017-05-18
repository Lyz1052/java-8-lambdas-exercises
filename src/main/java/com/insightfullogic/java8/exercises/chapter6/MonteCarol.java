package com.insightfullogic.java8.exercises.chapter6;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

/**
 * Created by zly on 2017/5/17.
 */
public class MonteCarol {
    static class Dice{
        static long random(ThreadLocalRandom random){
            return random.nextInt(1,7);
        }

        static long random(int throwCount){
            ThreadLocalRandom random = ThreadLocalRandom.current();
            return IntStream.range(0,throwCount).mapToLong(n->random(random)).sum();
        }
    }

    static Map<Integer,Double> Pmap(Integer throwCount,Integer tryCount){
        Double p = 1/tryCount.doubleValue();

        return IntStream.range(0,tryCount)
                .parallel()
                .mapToObj((i)->Long.valueOf(Dice.random(throwCount)).intValue())
                .collect(groupingBy(n->n, summingDouble(n->p)));
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Map<Integer,Double> pmap = Pmap(3,1000000);
//        Map<Integer,Double> pmap = parallelDiceRolls(1000000);
        System.out.println(
        pmap.entrySet().stream()
                .map(entry->"sum: "+entry.getKey()+",probability: "+entry.getValue())
                    .collect(Collectors.joining("\n","total time(ms):"+String.valueOf(System.currentTimeMillis()-start)+"\n",""))
        );
    }


    public static Map<Integer, Double> parallelDiceRolls(int N) {
        double fraction = 1.0 / N;

        return IntStream.range(0, N)                        // <1>
                .parallel()                         // <2>
                .mapToObj(twoDiceThrows())          // <3>
                .collect(groupingBy(side -> side,   // <4>
                        summingDouble(n -> fraction))); // <5>
    }
    // END parallel

    private static IntFunction<Integer> twoDiceThrows() {
        return i -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int firstThrow = random.nextInt(1, 7);
            int secondThrow = random.nextInt(1, 7);
            return firstThrow + secondThrow;
        };
    }
}
