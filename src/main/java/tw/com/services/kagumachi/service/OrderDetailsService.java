
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
import tw.com.services.kagumachi.model.ProductImage;
import tw.com.services.kagumachi.repository.OrderDetailRepository;
import tw.com.services.kagumachi.repository.OrderRepository;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;
import tw.com.services.kagumachi.repository.ProductRepository;

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
	
	@Autowired
	private ProductRepository productRepository;
	
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
			Integer productId = orderdetail.getProduct().getProductid();
			Integer colorsId = orderdetail.getProductColor().getColorsid();
			dto.setOrderdetailid(orderdetail.getOrderdetailid());
			dto.setProductname(orderdetail.getProduct().getProductname());
			dto.setColorname(orderdetail.getProductColor().getColorname());
			dto.setPrice(orderdetail.getPrice());
			dto.setQuantity(orderdetail.getQuantity());
			dto.setProductid(productId);
			dto.setColorsid(colorsId);
			dto.setOrderid(orderdetail.getOrder().getOrderid());		
			
			Optional<ProductImage> image = productImageRepository.findByProduct_ProductidAndProductColor_ColorsidAndIsprimary(productId, colorsId, true);
			dto.setImageurl(image.get().getImageurl());

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

	// by ChongWei
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
            
            orderDetail.setPrice(productRepository.findById(dto.getProductId()).get().getDiscountprice());
            
            orderDetailRepository.save(orderDetail); // 儲存到資料庫
            
            // 更新庫存
            updateProductStock(dto.getProductId(), dto.getColorsId(), dto.getQuantity());
        }
    }
	
	private void updateProductStock(int productId, int colorsId, int quantity) {
		// 查找對應的 ProductColor
        Optional<ProductColor> productColorOpt = productColorRepository.findById(colorsId);
        if (productColorOpt.isPresent()) {
            ProductColor productColor = productColorOpt.get();
            
            // 扣除庫存
            int newStock = productColor.getStock() - quantity;
            if (newStock < 0) {
                throw new IllegalStateException("庫存不足，無法處理訂單");
            }

            productColor.setStock(newStock);
            productColorRepository.save(productColor);
            System.out.println("更新庫存: productId=" + productId + ", colorsId=" + colorsId + ", 新庫存=" + newStock);
        } else {
            throw new IllegalArgumentException("找不到對應的 ProductColor，productId=" + productId + ", colorsId=" + colorsId);
        }
	}
}
