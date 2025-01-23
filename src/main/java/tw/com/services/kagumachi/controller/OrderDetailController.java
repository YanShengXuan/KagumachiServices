package tw.com.services.kagumachi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.dto.OrderDetailDTO;
import tw.com.services.kagumachi.dto.OrderDetailsDto;
import tw.com.services.kagumachi.service.OrderDetailsService;

@RestController
@RequestMapping("/orderdetail")
public class OrderDetailController {

	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@GetMapping("{orderid}")
	public ResponseEntity<List<OrderDetailsDto>> getDetails(@PathVariable("orderid") String orderSerial) {
		List<OrderDetailsDto> orderDetails = orderDetailsService.getDetailsByOrderserial(orderSerial);
        return ResponseEntity.ok(orderDetails);
    }
	
	@PostMapping("/{orderId}")
    public ResponseEntity<String> saveOrderDetails(
//	public void saveOrderDetails(
            @PathVariable Integer orderId,
            @RequestBody List<OrderDetailDTO> OrderDetailDTOs) {
		System.out.println("Received order details: " + OrderDetailDTOs);
        try {
            orderDetailsService.saveOrderDetails(orderId, OrderDetailDTOs);
            return ResponseEntity.ok("Order details saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
