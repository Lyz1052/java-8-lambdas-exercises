package com.insightfullogic.java8.exercises.chapter3;

import java.util.List;
import java.util.Optional;

public class StringExercises {

    // Question 7
    public static int countLowercaseLetters(String string) {
        return Long.valueOf(string.chars().filter(c->c>=97&&c<=122).count()).intValue();
    }

    // Question 8
    public static Optional<String> mostLowercaseString(List<String> strings) {
        Integer max = strings.stream().mapToInt(str->countLowercaseLetters(str)).max().getAsInt();
        return strings.stream().filter(str ->countLowercaseLetters(str)==max).findFirst();
        //使用Comparator接口
        //return strings.stream()
        //        .max(Comparator.comparing(StringExercises::countLowercaseLetters));
    }

}
