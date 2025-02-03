package tw.com.services.kagumachi.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.services.kagumachi.model.Logistics;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.repository.CartRepository;
import tw.com.services.kagumachi.repository.LogisticsRepository;
import tw.com.services.kagumachi.repository.OrderRepository;
import tw.com.services.kagumachi.repository.ProductColorRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
    CartRepository cartRepository;
	
	@Autowired
    ProductColorRepository productColorRepository;
	
	@Autowired
    LogisticsRepository logisticsRepository;
	
	public Integer getOrderIdbyOrderserial(String orderSerial){
		return orderRepository.findByOrderserial(orderSerial);
	}
	
	
	public Integer createOrderAndReturnId(Integer memberid, Order order) {
        // 設定訂單日期
        order.setOrderdate(LocalDate.now());
        
        // 綁定會員
        Member member = new Member();
        member.setMemberid(memberid);
        order.setMember(member);
        
        // 物流日期
        LocalDate estimateddeliverydate = order.getEstimateddeliverydate(); // 到貨日期
        order.setDeliverydate(estimateddeliverydate.plusDays(-1L)); // 出貨日期
        
        // 隨機選擇物流
        List<Logistics> logisticsList = logisticsRepository.findAllLogistics();
        if (!logisticsList.isEmpty()) {
            Random random = new Random();
            Logistics selectedLogistics = logisticsList.get(random.nextInt(logisticsList.size()));
            order.setLogistics(selectedLogistics); // 將隨機選擇的物流綁定到訂單
            
         // 生成物流單號
            String logisticsNumber = generateLogisticsNumber(selectedLogistics.getLogisticsid());
            order.setLogisticsnumber(logisticsNumber);
        } else {
            throw new RuntimeException("無可用的物流公司");
        }
        
        // 保存訂單到資料庫
        orderRepository.save(order);
        
        // 刪除 carts 中 isPurchase = 1 的資料
        cartRepository.deleteAll(cartRepository.findByMember_MemberidAndIspurchase(memberid, true));
        
        // 返回生成的 orderId
        return order.getOrderid();
    }
	
	// 物流單號生成
    private String generateLogisticsNumber(Integer logisticsId) {
        String prefix;
        switch (logisticsId) {
            case 1:
                prefix = "CAT"; // 黑貓宅急便
                break;
            case 2:
                prefix = "KTJ"; // 嘉里大榮貨運
                break;
            default:
                prefix = "LOG"; // 預設物流
        }
        return prefix + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
