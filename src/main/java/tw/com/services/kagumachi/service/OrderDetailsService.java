package tw.com.services.kagumachi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.dto.OrderDetailsDto;
import tw.com.services.kagumachi.model.OrderDetail;
import tw.com.services.kagumachi.model.ProductImage;
import tw.com.services.kagumachi.repository.OrderDetailRepository;
import tw.com.services.kagumachi.repository.ProductColorRepository;

@Service
public class OrderDetailsService {
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	ProductColorRepository productColorRepository;
	
	public List<OrderDetailsDto> getDetails(@Param("orderId") Integer orderId){
		List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_Orderid(orderId);
		
		List<OrderDetailsDto> result = new ArrayList<OrderDetailsDto>();
		for(OrderDetail orderDetail : orderDetails) {
			OrderDetailsDto dto = new OrderDetailsDto();
			int unitprice = orderDetail.getProduct().getUnitprice();
			int discountprice = orderDetail.getProduct().getDiscountprice() != null ? orderDetail.getProduct().getDiscountprice() : 0;
			dto.setOrderdetailid(orderDetail.getOrderdetailid());
			dto.setProductname(orderDetail.getProduct().getProductname());
			dto.setColorname(orderDetail.getProductColor().getColorname());
			dto.setPrice(discountprice > 0 ? discountprice : unitprice);
			dto.setQuantity(orderDetail.getQuantity());
			result.add(dto);
		}
		return result;
	}

}
