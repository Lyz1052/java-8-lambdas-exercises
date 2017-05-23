package com.insightfullogic.java8.exercises.chapter8.dsl;

/**
 * Created by zly on 2017/5/19.
 */
public final class Expecter {
    private Object expectObj = null;

    Expecter that(Object object){
        this.expectObj = object;
        return this;
    }

    void isEqualTo(Object object){
        Assert("Equal check",object!=null&&object.equals(expectObj));
    }

    void isEmpty(){
        Assert("Empty check", expectObj == null);
    }

    private void Assert(String message,boolean res) throws RuntimeException{
        if(!res) throw new RuntimeException(message+" ERROR");
    }
}
