package tw.com.services.kagumachi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.services.kagumachi.model.*;
import tw.com.services.kagumachi.repository.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/productcart")
public class ProductCartKeepController {
    @Autowired
    private MyKeepRepository myKeepRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductColorRepository productColorRepository;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/addMyKeep")
    public ResponseEntity<Map<String, String>> addToFavorite(@RequestBody Map<String, Integer> payload) {
        Integer memberid = payload.get("memberid");
        Integer productid = payload.get("productid");

        if (memberid == null || productid == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "缺少必要參數"));
        }

        Member member = memberRepository.findById(memberid)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Product product = productRepository.findById(productid)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 檢查是否已收藏
        Optional<MyKeep> existingFavorite = myKeepRepository.findByMemberAndProduct(member, product);
        if (existingFavorite.isPresent()) {
            return ResponseEntity.ok(Map.of("message", "已收藏"));
        }

        MyKeep myKeep = new MyKeep();
        myKeep.setMember(member);
        myKeep.setProduct(product);
        myKeepRepository.save(myKeep);

        return ResponseEntity.ok(Map.of("message", "已加入收藏"));
    }


    @PostMapping("/addToCart")
    public ResponseEntity<Map<String, String>> addToCart(@RequestBody Map<String, Object> payload) {
        Integer memberid = (Integer) payload.get("memberid");
        Integer productid = (Integer) payload.get("productid");
        String colorname = ((String) payload.get("color")).replace("'", "\"");
        Integer quantity = (Integer) payload.get("quantity");

        Member member = memberRepository.findById(memberid)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Product product = productRepository.findById(productid)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductColor productColor = productColorRepository.findByProduct_ProductidAndColorname(productid, colorname)
                .orElseThrow(() -> new RuntimeException("ProductColor not found"));

        Optional<Cart> existingCart = cartRepository.findByMember_MemberidAndProduct_ProductidAndProductColor_Colorsid(
                memberid, productid, productColor.getColorsid());

        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            cartRepository.save(cart);
        } else {
            Cart cart = new Cart();
            cart.setMember(member);
            cart.setProduct(product);
            cart.setProductColor(productColor);
            cart.setQuantity(quantity);
            cart.setIspurchase(true);
            cartRepository.save(cart);
        }

        return ResponseEntity.ok(Map.of("message", "Added successfully"));
    }
}
