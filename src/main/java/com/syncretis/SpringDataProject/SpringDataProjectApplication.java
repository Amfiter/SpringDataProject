package com.syncretis.SpringDataProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringDataProjectApplication {

    public static void main(String[] args) {
        /*ConfigurableApplicationContext run = SpringApplication.run(SpringDataProjectApplication.class, args);
        Config config = run.getBean("config", Config.class);
        config.run();*/
        SpringApplication.run(SpringDataProjectApplication.class, args);
    }
}