package tw.com.services.kagumachi.service;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public Integer getOrderIdbyOrderserial(String orderSerial){
		return orderRepository.findByOrderserial(orderSerial);
	}
	
	public void createOrder(Integer memberid, Order order) {
        // 設定訂單日期
        order.setOrderdate(LocalDate.now());
        
        Member member = new Member();
        member.setMemberid(memberid);
        order.setMember(member);
        
        // 保存訂單到資料庫
        orderRepository.save(order);
    }

}
