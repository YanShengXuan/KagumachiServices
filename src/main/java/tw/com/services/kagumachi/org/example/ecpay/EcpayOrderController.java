package tw.com.services.kagumachi.org.example.ecpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EcpayOrderController {

    @Autowired
    EcpayOrderService ecpayOrderService;

    @PostMapping("/ecpayCheckout/{memberId}")
    public String ecpayCheckout(@PathVariable int memberId, @RequestBody EcpayDTO ecpayDTO) {
        String aioCheckOutALLForm = ecpayOrderService.ecpayCheckout(memberId, ecpayDTO);
        return aioCheckOutALLForm;
    }
}