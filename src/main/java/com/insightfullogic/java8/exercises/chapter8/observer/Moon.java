package com.insightfullogic.java8.exercises.chapter8.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zly on 2017/5/18.
 */
public class Moon {
    List<Prober> probers = new ArrayList<>();

    void addProber(Prober prober){
        probers.add(prober);
    }

    void event(String name){
        probers.forEach(prober -> prober.event(name));
    }

    public static void main(String[] args) {
        Moon moon = new Moon();

        //triditional solution
        EvilProber evilProber = new EvilProber();
        HolyProber holyProber = new HolyProber();
        moon.addProber(evilProber::event);
        moon.addProber(holyProber::event);

        //using lambda
        moon.addProber(name-> System.out.println(name+" occured, ready to invade"));
        moon.addProber(name-> System.out.println(name+" occured, ready to defence"));

        moon.event("moon landing ");
    }
}
