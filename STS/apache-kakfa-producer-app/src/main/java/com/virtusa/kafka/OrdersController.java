package com.virtusa.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {
	
	//@Autowired
	//KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	KafkaTemplate<String,Order> kafkaTemplate;
	
	private static final String TOPIC = "OrderTopic";
	
//	@GetMapping("/publish/{message}")
//	public String publishOrderInfo(@PathVariable("message") final String message) {
//		
//		kafkaTemplate.send(TOPIC, message);
//		return "Published successfully";
	
	@PostMapping("/publish")
	public String publishOrderIngo(@RequestBody Order order) {
		kafkaTemplate.send(TOPIC, order);
		return "Published successfully";
	}

}
