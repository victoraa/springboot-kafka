package com.example.kafka.springbootkafka;

import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "test_topic", groupId = "grupoPrueba")
	public void consumeMessage(ConsumerRecord<String, String> cr, String message) {

		System.out.println(message);
		logger.info("Logger received key {}: Type [{}] | Mensaje: {} | Record: {}", cr.key(),
				typeIdHeader(cr.headers()), message, cr.toString());
	}

	private static String typeIdHeader(Headers headers) {
		return StreamSupport.stream(headers.spliterator(), false).filter(header -> header.key().equals("__TypeId__"))
				.findFirst().map(header -> new String(header.value())).orElse("N/A");
	}

//	@KafkaListener(groupId = "group_id", topicPartitions = @TopicPartition(topic = "test_topic", partitionOffsets = {
//			@PartitionOffset(partition = "0", initialOffset = "0") }))
//	void listenToPartitionWithOffset(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
//			@Header(KafkaHeaders.OFFSET) int offset) {
//		logger.info("Received message [{}] from partition-{} with offset-{}", message, partition, offset);
//	}
}
