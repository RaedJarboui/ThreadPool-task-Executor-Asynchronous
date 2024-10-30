package com.spring.boot.async.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.async.model.Order;
import com.spring.boot.async.service.OrderFulfillmentService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderFulfillmentService service;

	@PostMapping
	public ResponseEntity<Order> processOrder(@RequestBody Order order) throws InterruptedException {
		service.processOrder(order); // synchronous call
		// asynchronous call
		service.notifyUser(order);
		service.assignVendor(order);
		service.packaging(order);
		service.assignDeliveryPartner(order);
		service.assignTrailerAndDispatch(order);
		return ResponseEntity.ok(order);
	}

}
