package tw.com.services.kagumachi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.dto.CartResponseDto;
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
	
	public List<CartResponseDto> getCartAndImg(@Param("memberId") Integer memberId){
		List<Cart> carts = cartRepository.findByMember_Memberid(memberId);
		
		List<CartResponseDto> result = new ArrayList<CartResponseDto>();
		for(Cart cart : carts) {
			CartResponseDto dto = new CartResponseDto();
			dto.setCartsid(cart.getCartsid());
			dto.setProductname(cart.getProduct().getProductname());
			dto.setColorname(cart.getProductColor().getColorname());
			dto.setQuantity(cart.getQuantity());
			dto.setUnitprice(cart.getProduct().getUnitprice());
			dto.setIspurchase(cart.getIspurchase());
			Optional<ProductImage> productImage = productImageRepository.findByProduct_ProductidAndProductColor_Colorsid(cart.getProduct().getProductid(), cart.getProductColor().getColorsid());
			dto.setImageurl(productImage.get().getImageurl());
			result.add(dto);
		}
		return result;
	}
}
