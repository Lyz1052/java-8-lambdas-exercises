package com.insightfullogic.java8.exercises.chapter8.observer;

/**
 * Created by zly on 2017/5/18.
 */
public class HolyProber implements Prober {
    @Override
    public void event(String name) {
        System.out.println(name+" occured, ready to defence");
    }
}
