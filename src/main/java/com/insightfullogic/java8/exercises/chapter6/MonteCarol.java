package com.insightfullogic.java8.exercises.chapter6;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by zly on 2017/5/17.
 */
public class MonteCarol {
    static class Dice{
        static long random(){
            return Math.round(Math.random()*6+.5);
        }

        static long random(int throwCount){
            return IntStream.of(throwCount).mapToLong(Dice::random).sum();
        }
    }

    static Map<Integer,Double> Pmap(Integer throwCount,Integer tryCount){
        Map<Integer,Long> map =  IntStream.of(tryCount).parallel().mapToObj((i)->Long.valueOf(Dice.random(throwCount)).intValue())
                .collect(Collectors.groupingBy(n->n,Collectors.counting()));
        return null;
    }
    public static void main(String[] args) {

    }
}
