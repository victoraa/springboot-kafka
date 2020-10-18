package com.example.kafka.springbootkafka;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private Producer producer;

	@GetMapping("/publish/{mensaje}")
	public void messageToTopic(@PathVariable("mensaje") String mensaje) {

		this.producer.sendMessage(mensaje + " - " + new Date());

	}
}
