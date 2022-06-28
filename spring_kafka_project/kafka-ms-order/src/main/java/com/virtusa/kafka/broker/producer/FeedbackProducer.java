package com.virtusa.kafka.broker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.virtusa.kafka.broker.message.FeedbackMessage;

@Service
public class FeedbackProducer {

	@Autowired
	private KafkaTemplate<String, FeedbackMessage> kafkaTemplate;

	public void publish(FeedbackMessage message) {
		kafkaTemplate.send("t-commodity-feedback", message);
	}

}