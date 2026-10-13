package com.project.pet.learning_management_system;

import com.project.pet.LearningManagementSystemApplication;
import org.springframework.boot.SpringApplication;

public class TestLearningManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(LearningManagementSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
