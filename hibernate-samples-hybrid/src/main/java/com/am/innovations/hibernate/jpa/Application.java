package com.am.innovations.hibernate.jpa;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableJpaAuditing
@ActiveProfiles("test")
public class Application implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 10; i++) {
            userRepository.save(new User("user." + i));
        }
    }

}

@Controller
@RequestMapping("/api")
class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public User get(@RequestParam(value = "userID") Long userID) throws InterruptedException, ExecutionException {
        return userRepository.findById(userID).get();
    }

}
