package com.virtusa.kafka.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.kafka.api.request.PromotionRequest;
import com.virtusa.kafka.command.action.PromotionAction;

@Service
public class PromotionService {

	@Autowired
	private PromotionAction action;
	
	public void createPromotion(PromotionRequest request) {
		action.publishToKafka(request);
	}
	
}