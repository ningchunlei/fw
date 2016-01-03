package com.sqsd.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
public class CliServiceApplication {

    public static ConfigurableApplicationContext run(Object source, String[] args) {
        return  run(new Object[] { source }, args);
    }

    public static ConfigurableApplicationContext run(Object[] sources, String[] args) {
        /*System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));*/

        final Object wait = new Object();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run(){
                synchronized (wait) {
                    wait.notify();

                }
            }
        });
        Thread daemon = new Thread("startup-thread") {
            public void run() {
                synchronized (wait){
                    try {
                        wait.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        daemon.start();

        List<Object> sourcesExt = new ArrayList<>(Arrays.asList(sources));
        sourcesExt.add(CliServiceApplication.class);
        SpringApplication app = new SpringApplication(sourcesExt.toArray());
        return app.run(args);
    }

}
