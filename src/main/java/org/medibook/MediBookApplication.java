package org.medibook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MediBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediBookApplication.class, args);
    }

}
