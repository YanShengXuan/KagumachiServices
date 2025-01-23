package tw.com.services.kagumachi.service;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.repository.CartRepository;
import tw.com.services.kagumachi.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
    CartRepository cartRepository;
	
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
        
        // 保存訂單到資料庫
        orderRepository.save(order);
        
        // 刪除 carts 中 ispurchase = true 的資料
        cartRepository.deleteAll(cartRepository.findByMember_MemberidAndIspurchase(memberid, true));
        
        // 返回生成的 orderId
        return order.getOrderid();
    }

}
