package com.insightfullogic.java8.exercises.chapter2;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Question2 {

    public static ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(()->{
        return new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy"));
    });

    public static void main(String[] args) {
        System.out.println(formatter.get().getFormat().format(new Date()));
        List<String> list = Stream.of("a","b","c").collect(toList());
    }

}
