package com.sample.trelloclone;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Trello Clone API", version = "0.0.1-SNAPSHOT", description = "Trello Clone API"))

public class TrellocloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrellocloneApplication.class, args);
    }

}
