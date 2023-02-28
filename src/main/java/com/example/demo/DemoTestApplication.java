package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.ToDo;
import com.example.demo.repository.ToDoRepository;

@SpringBootApplication
public class DemoTestApplication implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(DemoTestApplication.class);
	@Autowired
	ToDoRepository doRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoTestApplication.class, args);
	}

	public void run(String... args) throws Exception {
		doRepository.save(new ToDo("Remove unused imports", true));
		doRepository.save(new ToDo("Clean the code", true));
		doRepository.save(new ToDo("Build the artifacts", false));
		doRepository.save(new ToDo("Deploy the jar file", true));
		logger.info("The sample data has been generated");
	}

}
