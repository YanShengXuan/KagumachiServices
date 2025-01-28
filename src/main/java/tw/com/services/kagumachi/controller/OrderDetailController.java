package tw.com.services.kagumachi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.dto.OrderDeliveryDataDto;
import tw.com.services.kagumachi.dto.OrderDetailsDto;
import tw.com.services.kagumachi.service.OrderDetailsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/orderdetail")
public class OrderDetailController {

	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@GetMapping("{orderserial}")
	public ResponseEntity<List<OrderDetailsDto>> getDetails(@PathVariable("orderserial") String orderSerial) {
		List<OrderDetailsDto> orderDetails = orderDetailsService.getDetailsByOrderserial(orderSerial);
        return ResponseEntity.ok(orderDetails);
    }
	
	@GetMapping("/delivery/{orderserial}")
	public ResponseEntity<OrderDeliveryDataDto> getDeliveyData(@PathVariable("orderserial") String orderSerial) {
		OrderDeliveryDataDto orerDeliveryData = orderDetailsService.getDeliveryDataByOrderserial(orderSerial);
		return ResponseEntity.ok(orerDeliveryData);
	}
}
