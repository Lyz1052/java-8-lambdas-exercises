package com.insightfullogic.java8.exercises.chapter9.chat;

import io.vertx.core.AbstractVerticle;

/**
 * @see  <a href="https://github.com/vert-x3/vertx-examples/blob/master/core-examples/src/main/java/io/vertx/example/core/http/sendfile/SendFile.java">SendFile</>
 * Created by zly on 2017/5/19.
 */
public class ChatVerticle extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runExample(ChatVerticle.class);
    }

    @Override
    public void start() throws Exception {
        String rootPath = "G:/";

        // In reality it's highly recommend you use Vert.x-Web for applications like this.

        vertx.createHttpServer().requestHandler(req -> {
            String filename = rootPath + req.path().replace("/", "");

            if (filename != null) {
                req.response().sendFile(filename);
            } else {
                req.response().setStatusCode(404).end();
            }

        }).listen(8080);
    }
}
