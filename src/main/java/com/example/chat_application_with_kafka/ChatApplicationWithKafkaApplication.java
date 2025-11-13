package com.example.chat_application_with_kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class ChatApplicationWithKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplicationWithKafkaApplication.class, args);
	}

}
