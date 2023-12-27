package me.kamran.jpa_demo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.kamran.jpa_demo.Repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

}
