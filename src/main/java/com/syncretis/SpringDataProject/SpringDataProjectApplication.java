package com.syncretis.SpringDataProject;

import com.syncretis.SpringDataProject.configs.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
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