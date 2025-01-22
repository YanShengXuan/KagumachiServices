package tw.com.services.kagumachi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public Integer getOrderIdbyOrderserial(String orderSerial){
		return orderRepository.findByOrderserial(orderSerial);
	}
}
