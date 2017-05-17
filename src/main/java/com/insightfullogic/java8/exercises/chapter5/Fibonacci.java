package com.insightfullogic.java8.exercises.chapter5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Fibonacci {

    public Fibonacci() {
    }

    public static long fibonacci(int x) {
        int[] arr = new int[x+1];
        arr[1] =1;
        return x==0?0:Arrays.stream(arr).collect(ConcurrentHashMap<Integer,Integer>::new,(map, ele)->{
            int size = map.size();
            if(size>1){
                map.computeIfAbsent(size,(s)->map.get(s-1)+map.get(s-2));
            }else{
                map.put(size,ele);
            }
        },(map1,map2)->map1.putAll(map2)).reduceValues(1,(a,b)->a>=b?a:b);
    }

    public static void main(String[] args) {
        HashMap<String,Integer> m = new HashMap<>();
        System.out.println(fibonacci(3));
    }

}
