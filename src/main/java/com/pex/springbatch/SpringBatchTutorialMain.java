package com.pex.springbatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://www.javacodegeeks.com/spring-batch-tutorial.html

@SpringBootApplication
public class SpringBatchTutorialMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchTutorialMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("running...");
    }

}
