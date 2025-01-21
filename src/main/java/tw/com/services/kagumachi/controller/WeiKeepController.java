package tw.com.services.kagumachi.controller;
<<<<<<< HEAD
import java.util.List;
import java.util.Map;
import java.util.Optional;
=======

import java.util.List;
import java.util.Map;
import java.util.Optional;

>>>>>>> c3216d5b9f0bf8518fa617be30724c034e3354e6
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD
=======

>>>>>>> c3216d5b9f0bf8518fa617be30724c034e3354e6
import tw.com.services.kagumachi.model.Cart;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.MyKeep;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductColor;
import tw.com.services.kagumachi.repository.CartRepository;
import tw.com.services.kagumachi.repository.MemberRepository;
import tw.com.services.kagumachi.repository.MyKeepRepository;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;
import tw.com.services.kagumachi.repository.ProductRepository;
<<<<<<< HEAD
=======


>>>>>>> c3216d5b9f0bf8518fa617be30724c034e3354e6
@RestController
@RequestMapping("/weikeep")
public class WeiKeepController {
	
	@Autowired
    private MyKeepRepository myKeepRepository;
<<<<<<< HEAD
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
=======

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

>>>>>>> c3216d5b9f0bf8518fa617be30724c034e3354e6
	
	@GetMapping("/test")
    public String getAllMyKeeps(@RequestParam Integer memberid) {
		List<MyKeep> myKeepsList = myKeepRepository.findByMember_Memberid(memberid);
		JSONArray jsonArray = new JSONArray();
		for (MyKeep myKeep : myKeepsList) {
            int productid = myKeep.getProduct().getProductid();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productid", productid);
            jsonObject.put("productname", myKeep.getProduct().getProductname());
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
    
<<<<<<< HEAD
    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, String>>addToMyKeep(@RequestBody Map<String, Integer> payload) {
    	Integer memberid = payload.get("memberid");
        Integer productid = payload.get("productid");
        MyKeep myKeep = new MyKeep(); 
        Member member = new Member();
        member.setMemberid(memberid);
        myKeep.setMember(member);
        Product product = new Product();
        product.setProductid(productid);
        myKeep.setProduct(product);
        myKeepRepository.save(myKeep);
        return ResponseEntity.ok(Map.of("message", "Add successfully"));
    }
}
=======
    @PostMapping("/{productId}")
    public ResponseEntity<String> addMyKeep(@RequestParam Integer memberid, @PathVariable Integer productid) {
        // 从数据库中获取 Member 和 Product 对象
        Member member = memberRepository.findById(memberid)
                                        .orElseThrow(() -> new RuntimeException("Member not found"));
        Product product = productRepository.findById(productid)
                                           .orElseThrow(() -> new RuntimeException("Product not found"));

        // 创建新的 MyKeep 实例并设置关联对象
        MyKeep myKeep = new MyKeep();
        myKeep.setMember(member);
        myKeep.setProduct(product);

        // 保存 MyKeep 实例到数据库
        myKeepRepository.save(myKeep);

        return ResponseEntity.ok("Product added to wishlist");
    }
    
}
>>>>>>> c3216d5b9f0bf8518fa617be30724c034e3354e6
