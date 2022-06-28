package com.virtusa.kafka.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.kafka.api.request.CustomerPurchaseMobileRequest;
import com.virtusa.kafka.api.request.CustomerPurchaseWebRequest;
import com.virtusa.kafka.api.response.PurchaseResponse;
import com.virtusa.kafka.command.service.CustomerPurchaseService;

@RestController
@RequestMapping("/api/customer/purchase")
public class CusomerPurchaseApi {
	
	@Autowired
	private CustomerPurchaseService service;
	
	@PostMapping(value = "/mobile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseResponse> createPurchaseMobile(
			@RequestBody CustomerPurchaseMobileRequest request
			) {
		var purchaseNumber = service.createPurchaseMobile(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new PurchaseResponse(purchaseNumber));
		
	}
	
	@PostMapping(value = "/web", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseResponse> createPurchaseWeb(
				@RequestBody CustomerPurchaseWebRequest request
			) {
		var purchaseNumber = service.createPurchaseWeb(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new PurchaseResponse(purchaseNumber));
		
	}

}