package com.sd_utcn.secondHand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.validation.annotation.Validated;

import com.sd_utcn.secondHand.UI.Login;

@SpringBootApplication
@Validated
public class Main extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }
    
    public static void main(String[] args) {
        SecondHandSystem system = new SecondHandSystem();
        new Login(system);
        SpringApplication.run(Main.class, args);
    }

}
