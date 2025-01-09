package tw.com.services.kagumachi.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tw.com.services.kagumachi.model.MyKeep;
import tw.com.services.kagumachi.model.ProductColor;
import tw.com.services.kagumachi.model.ProductImage;
import tw.com.services.kagumachi.repository.MyKeepRepository;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;

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
            // producklink還不知怎給，先不給。
            jsonObject.put("discountprice", myKeep.getProduct().getDiscountprice());

            List<ProductColor> productcolors = productColorRepository.findByProduct_Productid(productid);
            JSONArray productdetails = new JSONArray();
            for(int i = 0; i < productcolors.size(); i++) {
                ProductColor productcolor = productcolors.get(i);
                JSONObject productdetail = new JSONObject();
                productdetail.put("color", productcolor.getColorname());
                productdetail.put("qty", productcolor.getStock());
                int colorsid = productcolor.getColorsid();
                Optional<ProductImage> productImage = productImageRepository.findByProduct_ProductidAndProductColor_colorsid(productid, colorsid);
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
}
