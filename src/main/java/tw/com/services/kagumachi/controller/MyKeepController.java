package tw.com.services.kagumachi.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.model.*;
import tw.com.services.kagumachi.repository.*;

import java.util.*;

@RestController
@RequestMapping("/mykeep")
public class MyKeepController {
    @Autowired
    private MyKeepRepository myKeepRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductColorRepository productColorRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String getAllMyKeeps(@RequestParam Integer memberid) {
        JSONArray jsonArray = new JSONArray();
//        memberid 應是從前端送來，再根據該id去找出該會員的收藏。
        List<MyKeep> myKeepsList = myKeepRepository.findByMember_Memberid(memberid);
        Set<Integer> processedProductIds = new HashSet<>();
        for (MyKeep myKeep : myKeepsList) {
            int productid = myKeep.getProduct().getProductid();
            if (processedProductIds.contains(productid)) {
                continue; // 遇到重複的productid就跳過 (可能某頁面對某商品重複新增收藏)
            }
            processedProductIds.add(productid);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productid", productid);
            jsonObject.put("productname", myKeep.getProduct().getProductname());
            jsonObject.put("width", myKeep.getProduct().getWidth());
            jsonObject.put("depth", myKeep.getProduct().getDepth());
            jsonObject.put("height", myKeep.getProduct().getHeight());
            jsonObject.put("discountprice", myKeep.getProduct().getDiscountprice());

            List<ProductColor> productcolors = productColorRepository.findByProduct_Productid(productid);
            JSONArray productdetails = new JSONArray();
            for (ProductColor productcolor : productcolors) {
                JSONObject productdetail = new JSONObject();
                productdetail.put("color", productcolor.getColorname());
                productdetail.put("qty", productcolor.getStock());
                int colorsid = productcolor.getColorsid();
                Optional<ProductImage> productImage = productImageRepository.findByProduct_ProductidAndProductColor_Colorsid(productid, colorsid);
                productdetail.put("imgsrc", productImage.isPresent() ? productImage.get().getImageurl() : "");
                productdetails.put(productdetail);
            }
            jsonObject.put("productdetails", productdetails);
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Map<String, String>> deleteMyKeep(@RequestBody Map<String, Integer> payload) {
        Integer memberid = payload.get("memberid");
        Integer productid = payload.get("productid");
        myKeepRepository.deleteByMember_MemberidAndProduct_Productid(memberid, productid);
        return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
    }

    @PostMapping
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
