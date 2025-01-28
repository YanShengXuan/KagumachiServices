package tw.com.services.kagumachi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.dto.CartDto;
import tw.com.services.kagumachi.model.Cart;
import tw.com.services.kagumachi.model.ProductImage;
import tw.com.services.kagumachi.repository.CartRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;
import tw.com.services.kagumachi.service.CartService;

@RestController
@RequestMapping("/shoppingcart")
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductImageRepository productImageRepository;
	
	@GetMapping("/step1/{memberid}")
	public List<CartDto> carts(@PathVariable Integer memberid) {
		return cartService.getCartAndImg(memberid);
	}

	@PostMapping("/step1/add/{cartid}")
	public void addQuantity(@PathVariable Integer cartid) {
		Optional<Cart> carts = cartRepository.findById(cartid);
		Cart cart = carts.get();
		cart.setQuantity(cart.getQuantity() + 1);
		cartRepository.save(cart);
	}

	@PostMapping("/step1/sub/{cartid}")
	public void subQuantity(@PathVariable Integer cartid) {
		Optional<Cart> carts = cartRepository.findById(cartid);
		Cart cart = carts.get();
		cart.setQuantity(cart.getQuantity() - 1);
		cartRepository.save(cart);
	}

	@PostMapping("/step1/purchase/{cartid}")
	public void purchase(@PathVariable Integer cartid) {
		Optional<Cart> carts = cartRepository.findById(cartid);
		Cart cart = carts.get();
		cart.setIspurchase(!cart.getIspurchase());
		cartRepository.save(cart);
	}

	@PostMapping("/step1/purchaseAll/{memberid}")
	public void purchaseAll(@PathVariable Integer memberid) {
		List<Cart> carts = cartRepository.findByMember_Memberid(memberid);
		boolean state = carts.stream().anyMatch(cart -> !cart.getIspurchase());

		for(Cart cart : carts) {
			cart.setIspurchase(state);
		}

		cartRepository.saveAll(carts);
	}
	
	@PostMapping("/step1/delete/{memberid}")
	public void delete(@PathVariable Integer memberid) {
		cartRepository.deleteById(memberid);
	}
}
