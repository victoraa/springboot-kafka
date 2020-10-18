package com.example.kafka.springbootkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootKafkaApplication {

	public static void main(String[] args) {
		ApplicationContext apl = SpringApplication.run(SpringbootKafkaApplication.class, args);
//		apl.getBean("")
	}

}
