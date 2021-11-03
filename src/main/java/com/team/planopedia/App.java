package com.team.planopedia;

import com.team.planopedia.ChoiceAlgorithm.ChoiceMaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
		//SpringApplication.run(App.class, args);

		ChoiceMaker test = new ChoiceMaker();
		test.makeDecisionTester();
	}
}