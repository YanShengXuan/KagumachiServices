package tw.com.services.kagumachi.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.repository.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("/shiporder")
public class ShipOrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String getAllShipOrders() {
        JSONArray jsonArray = new JSONArray();
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderid", order.getOrderid());
            jsonObject.put("orderNumber", order.getOrderserial());
            String comname = order.getLogistics().getComname();
            jsonObject.put("logisticsCompany", comname);
            jsonObject.put("logisticsNumber", order.getLogisticsnumber());
            jsonObject.put("orderdate", order.getOrderdate());
            jsonObject.put("deliverydate", order.getDeliverydate());
            jsonObject.put("estimateddeliverydate", order.getEstimateddeliverydate());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
}
