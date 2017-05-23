package com.insightfullogic.java8.exercises.chapter8.dsl;

import java.util.function.Consumer;

/**
 * Created by zly on 2017/5/19.
 */
public interface UnitTest {
    //书中的例子把Consumer也作为用户代码写出来了，这里简化成预定义的Consumer
    void should(String name, Consumer<Expecter> consumer) throws RuntimeException;
}
