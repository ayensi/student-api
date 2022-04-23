package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student okan = new Student(
                    "Okan",
                    "okan@gmail.com",
                    LocalDate.of(1999, Month.AUGUST,25)
            );
            Student tolga = new Student(
                    "Tolga",
                    "tolga@gmail.com",
                    LocalDate.of(2000, Month.AUGUST,14)
            );
            repository.saveAll(
                    List.of(okan,tolga)
            );
        };
    }
}
