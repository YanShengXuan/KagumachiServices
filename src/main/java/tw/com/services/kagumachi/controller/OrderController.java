package tw.com.services.kagumachi.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.dto.OrderDetailsDto;
import tw.com.services.kagumachi.dto.OrderDto;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.model.OrderDetail;
import tw.com.services.kagumachi.repository.OrderDetailRepository;
import tw.com.services.kagumachi.repository.OrderRepository;
import tw.com.services.kagumachi.service.OrderDetailsService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailsService orderDetailsService;
	
	@GetMapping("/test")
	public List<Order> test(){
		return orderRepository.findAll();
	}
	
	@PostMapping("/between")
	public List<Order> between(@RequestBody OrderDto orderDto){
		return orderRepository.findByDateBetween(orderDto.getStartDate(), orderDto.getEndDate());
	}
	
	@PostMapping("/totalpriceASC")
	public List<Order> totalpriceASC(@RequestBody OrderDto orderDto){
		return orderRepository.findByDateBetweenTotalprice(orderDto.getStartDate(), orderDto.getEndDate());
	}
	
	@PostMapping("/orderstatusASC")
	public List<Order> OrderstatusASC(@RequestBody OrderDto orderDto){
		return orderRepository.findByDateBetweenOrderstatus(orderDto.getStartDate(), orderDto.getEndDate());
	}
	
	@GetMapping("/details/{orderid}")
	public List<OrderDetailsDto> details(@PathVariable Integer orderid){
		return orderDetailsService.getDetails(orderid);
	}
	
	
	
}
