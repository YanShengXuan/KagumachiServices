package tw.com.services.kagumachi.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.repository.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/myorders")
public class MyOrdersController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String getAllMyOrders(@RequestParam Integer memberid) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray jsonArray = new JSONArray();
        List<Order> orders = orderRepository.findByMember_Memberid(memberid);
        for (Order order : orders) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderid", order.getOrderid());
            jsonObject.put("orderdate", ft.format(order.getOrderdate()));
            jsonObject.put("ordernumber", order.getOrderserial());
            jsonObject.put("paymentmethod", order.getPaymentmethod());
            jsonObject.put("orderstatus", order.getOrderstatus());
            jsonObject.put("deliverydate", ft.format(order.getDeliverydate()));
            jsonObject.put("estimateddeliverydate", ft.format(order.getEstimateddeliverydate()));
            jsonObject.put("totalprice", order.getTotalprice());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
}
