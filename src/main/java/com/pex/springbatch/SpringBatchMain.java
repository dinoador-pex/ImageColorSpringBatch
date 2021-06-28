package com.pex.springbatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("running...");
    }

}
