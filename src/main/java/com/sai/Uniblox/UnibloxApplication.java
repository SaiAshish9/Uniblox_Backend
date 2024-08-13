package com.sai.Uniblox;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.sai.Uniblox.entity")
@EnableJpaRepositories(basePackages = "com.sai.Uniblox.repository")
public class UnibloxApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(UnibloxApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String projectDir = System.getProperty("user.dir");
        System.out.println("Project Directory: " + projectDir);
    }
}
