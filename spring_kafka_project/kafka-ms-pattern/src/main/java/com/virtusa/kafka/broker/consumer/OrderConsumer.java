package com.virtusa.kafka.broker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.virtusa.kafka.broker.message.OrderMessage;

@Service
public class OrderConsumer {
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);
	
	@KafkaListener(topics = "t-commodity-order")
	public void listen(OrderMessage message) {
		var totalItemAmount = message.getPrice() * message.getQuantity();
		
		LOG.info(
				"Processing Order {}, item {}, creditcardnum {}. Total amount for this itme is {} ",
				message.getOrderNumber(),
				message.getItemName(),
				message.getCreditCardNumber(),
				totalItemAmount
				);
	}

}