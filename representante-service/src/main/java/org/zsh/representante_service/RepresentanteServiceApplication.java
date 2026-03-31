package org.zsh.representante_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RepresentanteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepresentanteServiceApplication.class, args);
    }
}
