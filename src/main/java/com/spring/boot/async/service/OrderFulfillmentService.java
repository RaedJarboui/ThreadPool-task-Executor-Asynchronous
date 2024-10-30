package com.spring.boot.async.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.async.model.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderFulfillmentService {
	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private PaymentService paymentService;

	public Order processOrder(Order order) throws InterruptedException {
		order.setTrackingId(UUID.randomUUID().toString());
		if (inventoryService.checkProductAvailability(order.getProductId())) {
			// handle exception here
			paymentService.processPayment(order);
		} else {
			throw new RuntimeException("Technical issue please retry");
		}
		return order;
	}

	public void notifyUser(Order order) throws InterruptedException {
		Thread.sleep(4000L);
		log.info("Notified to the user " + Thread.currentThread().getName());
	}

	public void assignVendor(Order order) throws InterruptedException {
		Thread.sleep(5000L);
		log.info("Assign order to vendor " + Thread.currentThread().getName());
	}

	public void packaging(Order order) throws InterruptedException {
		Thread.sleep(2000L);
		log.info("Order packaging completed " + Thread.currentThread().getName());
	}

	public void assignDeliveryPartner(Order order) throws InterruptedException {
		Thread.sleep(10000L);
		log.info("Delivery partner assigned " + Thread.currentThread().getName());
	}

	public void assignTrailerAndDispatch(Order order) throws InterruptedException {
		Thread.sleep(3000L);
		log.info("Trailer assigned and Order dispatched " + Thread.currentThread().getName());
	}
}
