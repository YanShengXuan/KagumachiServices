
package tw.com.services.kagumachi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.dto.OrderAllMonthDto;
import tw.com.services.kagumachi.dto.OrderDeliveryDataDto;
import tw.com.services.kagumachi.dto.OrderDetailDTO;
import tw.com.services.kagumachi.dto.OrderDetailsDto;
import tw.com.services.kagumachi.dto.OrderSeasonDto;
import tw.com.services.kagumachi.model.Order;
import tw.com.services.kagumachi.model.OrderDetail;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductColor;
import tw.com.services.kagumachi.repository.OrderDetailRepository;
import tw.com.services.kagumachi.repository.OrderRepository;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;

@Service
public class OrderDetailsService {
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Autowired
	ProductColorRepository productColorRepository;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductImageRepository productImageRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
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
	
	public List<OrderSeasonDto> getSeasonQuantity(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate){
		List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_OrderdateBetween(startDate, endDate);
		Map<String, Integer> mapData = new HashMap<String, Integer>();
		for(OrderDetail orderDetail : orderDetails) {
			String mapKey = orderDetail.getProduct().getSubCategory().getCategoryname();
			Integer mapValue = orderDetail.getQuantity();
			if(!mapData.containsKey(mapKey)) {
				mapData.put(mapKey, mapValue);
			}else {
				mapData.replace(mapKey, mapData.get(mapKey) + mapValue);
			}
		}
		List<OrderSeasonDto> result = mapData.entrySet().stream()
				.map(data -> new OrderSeasonDto(data.getKey(), data.getValue()))
				.collect(Collectors.toList());
		
		return result;
	}
	
	public List<OrderAllMonthDto> getAllMonthQuantity(@Param("year")Integer year){
		List<OrderAllMonthDto> orderAllMonthDtos = orderDetailRepository.findByOrder_Orderdate(year);
		BigDecimal init = new BigDecimal(0);
		Map<String, BigDecimal> mapData = new LinkedHashMap<String, BigDecimal>();
		mapData.put("01", init);mapData.put("02", init);mapData.put("03", init);mapData.put("04", init);
		mapData.put("05", init);mapData.put("06", init);mapData.put("07", init);mapData.put("08", init);
		mapData.put("09", init);mapData.put("10", init);mapData.put("11", init);mapData.put("12", init);
		for(OrderAllMonthDto orderAllMonthDto : orderAllMonthDtos) {
			mapData.put(orderAllMonthDto.getOrderdate(), orderAllMonthDto.getQuantity());
		}
		
		List<OrderAllMonthDto> result = mapData.entrySet().stream()
				.map(data -> new OrderAllMonthDto(data.getKey(), data.getValue()))
				.collect(Collectors.toList());
		return result;
	}
	
	public List<OrderDetailsDto> getDetailsByOrderserial(String orderSerial){
		Integer orderId = orderService.getOrderIdbyOrderserial(orderSerial);
		List<OrderDetail> orderdetails = orderDetailRepository.findByOrder_Orderid(orderId);

		List<OrderDetailsDto> result = new ArrayList<OrderDetailsDto>();
		for (OrderDetail orderdetail : orderdetails) {
			OrderDetailsDto dto = new OrderDetailsDto();
			int unitprice = orderdetail.getProduct().getUnitprice();
			int discountprice = orderdetail.getProduct().getDiscountprice() != null ? orderdetail.getProduct().getDiscountprice() : 0;
			dto.setOrderdetailid(orderdetail.getOrderdetailid());
			dto.setProductname(orderdetail.getProduct().getProductname());
			dto.setColorname(orderdetail.getProductColor().getColorname());
			dto.setPrice(discountprice > 0 ? discountprice : unitprice);
			dto.setQuantity(orderdetail.getQuantity());
			dto.setProductid(orderdetail.getProduct().getProductid());
			dto.setColorsid(orderdetail.getProductColor().getColorsid());
			dto.setOrderid(orderdetail.getOrder().getOrderid());		
			Integer productId = orderdetail.getProduct().getProductid();
			Optional<String> imageUrl = productImageRepository.findImageUrlsByProductId(productId);

			dto.setImageurl(imageUrl.orElseThrow(() -> 
            new IllegalStateException("商品 ID " + orderdetail.getProduct().getProductid() + " 找不到主圖片")));
			result.add(dto);
		}
		return result;
	}
	
	public OrderDeliveryDataDto getDeliveryDataByOrderserial(String orderSerial){
		Integer orderId = orderService.getOrderIdbyOrderserial(orderSerial);
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("找不到對應的訂單 orderId:" + orderId));
		
		OrderDeliveryDataDto dto = new OrderDeliveryDataDto();
		dto.setRecipient(order.getRecipient());
		dto.setPhone(order.getPhone());
		dto.setShippingmethod(order.getShippingmethod());
		dto.setOrdercity(order.getOrdercity());
		dto.setDistrict(order.getDistrict());
		dto.setAddress(order.getAddress());
		return dto;
	}

	public void saveOrderDetails(int orderId, List<OrderDetailDTO> OrderDetailDTOs) {
        // 將每個 orderDetail 設定對應的 orderId
        for (OrderDetailDTO dto : OrderDetailDTOs) {
            OrderDetail orderDetail = new OrderDetail();
            Order order = new Order();
            order.setOrderid(orderId);
            orderDetail.setOrder(order);
            
            Product product = new Product();
            product.setProductid(dto.getProductId());
            orderDetail.setProduct(product);
            
            ProductColor productColor = new ProductColor();
            productColor.setColorsid(dto.getColorsId());
            orderDetail.setProductColor(productColor);
            
            orderDetail.setQuantity(dto.getQuantity());
            
            orderDetailRepository.save(orderDetail); // 儲存到資料庫
        }
    }

}
