package com.insightfullogic.java8.exercises.chapter8.dsl;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by zly on 2017/5/19.
 */
public class Descriptor {
    public static void describe(String procedure, Consumer<UnitTest> proceduring){
        System.out.println("Start \""+procedure+"\" tests:\n");
        proceduring.accept((task,tasking)->{
            System.out.println("\""+task+"\":");
            try {
                tasking.accept(new Expecter());
                System.out.println("succeed!\n");
            }catch(RuntimeException e){
                System.out.println("failed :"+e.getMessage()+"\n");
            }
        });
    }

    public static void main(String[] args) {

        describe("a stack", it -> {
            it.should("be empty when created", expect -> {
                expect.that(new Stack()).isEmpty();
            });

            it.should("push new elements onto the top of the stack", expect -> {
                Stack<Integer> stack = new Stack<>();
                stack.push(1);
                expect.that(stack.get(0)).isEqualTo(1);
            });

            it.should("pop the last element pushed onto the stack", expect -> {
                Stack<Integer> stack = new Stack<>();
                stack.push(2);
                stack.push(1);
                expect.that(stack.pop()).isEqualTo(2);
            });
        });
    }
}
