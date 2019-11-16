package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Application  {
    @Autowired
    StudentJdbcRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}