package tw.com.services.kagumachi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.service.OrderSummaryService;

@RestController
@RequestMapping("/ordersummary")
public class OrderSummaryController {

	@Autowired
	private OrderSummaryService orderSummaryService;
	
	@GetMapping("/cartstep2/{memberid}")
	public String getOrderSummary(@PathVariable Integer memberid) {
        return orderSummaryService.getOrderSummary(memberid).toString();
    }
}
