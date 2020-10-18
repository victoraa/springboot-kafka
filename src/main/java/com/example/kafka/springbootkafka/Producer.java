package com.example.kafka.springbootkafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);

	private static final String TOPIC = "test_topic";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;


	public void sendMessage(String message) {

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("Message [{}] delivered with offset {}", message, result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.warn("Unable to deliver message [{}]. {}", message, ex.getMessage());
			}
		});

	}

	@Bean
	public NewTopic createTopic() {

		return new NewTopic(TOPIC, 3, (short) 1);
	}

}
