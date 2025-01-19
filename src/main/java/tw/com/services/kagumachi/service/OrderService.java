package tw.com.services.kagumachi.service;

import org.springframework.beans.factory.annotation.Autowired;

import tw.com.services.kagumachi.repository.OrderRepository;

public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
}
