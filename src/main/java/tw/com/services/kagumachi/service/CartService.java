package tw.com.services.kagumachi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.dto.CartDto;
import tw.com.services.kagumachi.model.Cart;
import tw.com.services.kagumachi.model.ProductImage;
import tw.com.services.kagumachi.repository.CartRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	public List<CartDto> getCartAndImg(@Param("memberId") Integer memberId){
		List<Cart> carts = cartRepository.findByMember_Memberid(memberId);
		
		List<CartDto> result = new ArrayList<CartDto>();
		for(Cart cart : carts) {
			CartDto dto = new CartDto();
			dto.setCartsid(cart.getCartsid());
			dto.setProductname(cart.getProduct().getProductname());
			dto.setColorname(cart.getProductColor().getColorname());
			dto.setQuantity(cart.getQuantity());
			dto.setUnitprice(cart.getProduct().getUnitprice());
			dto.setIspurchase(cart.getIspurchase());
//			Optional<ProductImage> productImage = productImageRepository.findByProduct_ProductidAndProductColor_Colorsid(cart.getProduct().getProductid(), cart.getProductColor().getColorsid());
//			dto.setImageurl(productImage.get().getImageurl());
			Optional<String> productImage = productImageRepository.findImageUrlsByProductId(cart.getProduct().getProductid());
			dto.setImageurl(productImage.get().toString());
			dto.setWidth(cart.getProduct().getWidth());
			dto.setHeight(cart.getProduct().getHeight());
			dto.setDepth(cart.getProduct().getDepth());
			dto.setDiscountprice(cart.getProduct().getDiscountprice());
			dto.setSalesname(cart.getProduct().getMainCategory().getSales() != null ? cart.getProduct().getMainCategory().getSales().getName() : "NULL");
			result.add(dto);
		}
		return result;
	}
}
