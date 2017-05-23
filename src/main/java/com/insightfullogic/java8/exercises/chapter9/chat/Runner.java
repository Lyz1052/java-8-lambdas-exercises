package com.insightfullogic.java8.exercises.chapter9.chat;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

/*
 * @see <a href="https://github.com/vert-x3/vertx-examples/blob/master/core-examples/src/main/java/io/vertx/example/util/Runner.java">Runner</a>
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Runner {

    private static final String CORE_EXAMPLES_DIR = "lambdas-book-examples";
    private static final String CORE_EXAMPLES_JAVA_DIR = CORE_EXAMPLES_DIR + "/src/main/java/";
    private static final String CORE_EXAMPLES_JS_DIR = CORE_EXAMPLES_DIR + "/src/main/js/";
    private static final String CORE_EXAMPLES_GROOVY_DIR = CORE_EXAMPLES_DIR + "/src/main/groovy/";
    private static final String CORE_EXAMPLES_RUBY_DIR = CORE_EXAMPLES_DIR + "/src/main/ruby/";

    public static void runClusteredExample(Class clazz) {
        runExample(CORE_EXAMPLES_JAVA_DIR, clazz, new VertxOptions().setClustered(true), null);
    }

    public static void runClusteredExample(Class clazz, VertxOptions options) {
        runExample(CORE_EXAMPLES_JAVA_DIR, clazz, options.setClustered(true), null);
    }

    public static void runExample(Class clazz) {
        runExample(CORE_EXAMPLES_JAVA_DIR, clazz, new VertxOptions().setClustered(false), null);
    }

    public static void runExample(Class clazz, DeploymentOptions options) {
        runExample(CORE_EXAMPLES_JAVA_DIR, clazz, new VertxOptions().setClustered(false), options);
    }

    // JavaScript examples

    public static void runJSExample(String scriptName) {
        runScriptExample(CORE_EXAMPLES_JS_DIR, scriptName, new VertxOptions().setClustered(false));
    }

    public static void runJSExampleClustered(String scriptName) {
        runScriptExample(CORE_EXAMPLES_JS_DIR, scriptName, new VertxOptions().setClustered(true));
    }


    // Ruby examples

    public static void runRubyExample(String scriptName) {
        runScriptExample(CORE_EXAMPLES_RUBY_DIR, scriptName, new VertxOptions().setClustered(false));
    }

    public static void runRubyExampleClustered(String scriptName) {
        runScriptExample(CORE_EXAMPLES_GROOVY_DIR, scriptName, new VertxOptions().setClustered(true));
    }



    public static void runExample(String exampleDir, Class clazz, VertxOptions options, DeploymentOptions
            deploymentOptions) {
        runExample(exampleDir + clazz.getPackage().getName().replace(".", "/"), clazz.getName(), options, deploymentOptions);
    }


    public static void runScriptExample(String prefix, String scriptName, VertxOptions options) {
        File file = new File(scriptName);
        String dirPart = file.getParent();
        String scriptDir = prefix + dirPart;
        runExample(scriptDir, scriptDir + "/" + file.getName(), options, null);
    }

    public static void runExample(String exampleDir, String verticleID, VertxOptions options, DeploymentOptions deploymentOptions) {
        if (options == null) {
            // Default parameter
            options = new VertxOptions();
        }
        // Smart cwd detection

        // Based on the current directory (.) and the desired directory (exampleDir), we try to compute the vertx.cwd
        // directory:
        try {
            // We need to use the canonical file. Without the file name is .
            File current = new File(".").getCanonicalFile();
            if (exampleDir.startsWith(current.getName())  && ! exampleDir.equals(current.getName())) {
                exampleDir = exampleDir.substring(current.getName().length() + 1);
            }
        } catch (IOException e) {
            // Ignore it.
        }

        //not necessary
        System.setProperty("vertx.cwd", exampleDir);
        Consumer<Vertx> runner = vertx -> {
            try {
                if (deploymentOptions != null) {
                    vertx.deployVerticle(verticleID, deploymentOptions);
                } else {
                    vertx.deployVerticle(verticleID);
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        };
        if (options.isClustered()) {
            Vertx.clusteredVertx(options, res -> {
                if (res.succeeded()) {
                    Vertx vertx = res.result();
                    runner.accept(vertx);
                } else {
                    res.cause().printStackTrace();
                }
            });
        } else {
            Vertx vertx = Vertx.vertx(options);
            runner.accept(vertx);
        }
    }
}