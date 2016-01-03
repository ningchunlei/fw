package com.sqsd.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Web 类型应用入口，对外提供FE接口
 */
@Configuration
@ComponentScan
public class HttpWebApplication {

    public static ConfigurableApplicationContext run(Object source, String[] args) {
        return  run(new Object[] { source }, args);
    }

    public static ConfigurableApplicationContext run(Object[] sources, String[] args) {
        /*System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));*/
        List<Object> sourcesExt = new ArrayList<>(Arrays.asList(sources));
        sourcesExt.add(HttpWebApplication.class);
        SpringApplication app = new SpringApplication(sourcesExt.toArray());
        return app.run(args);
    }
}
