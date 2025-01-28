package tw.com.services.kagumachi.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.model.Cart;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductImage;
import tw.com.services.kagumachi.repository.CartRepository;
import tw.com.services.kagumachi.repository.ProductImageRepository;
import tw.com.services.kagumachi.repository.ShipRateRepository;

@Service
public class OrderSummaryService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
    private ProductImageRepository productImageRepository;

	@Autowired
	private ShipRateRepository shipRateRepository;

	public JSONObject getOrderSummary(Integer memberid) {
		System.out.println(memberid);
		List<Cart> carts = cartRepository.findByMember_MemberidAndIspurchase(memberid, true);
		System.out.println(carts);

		int itemsCount = 0; // 總商品數量
		int totalPrice = 0; // 折扣後的商品總價
		int shippingFee = 0; // 預設運費
		
		JSONArray itemDetails = new JSONArray(); // 存放商品詳細資訊

		for (Cart cart : carts) {
			Product product = cart.getProduct(); // 各商品
			int quantity = cart.getQuantity(); // 各商品的數量
			Integer colorsId = cart.getProductColor().getColorsid(); // 顏色 ID
			

			Integer discountPrice = product.getDiscountprice();

			itemsCount += quantity; // 總商品數量
			totalPrice += discountPrice * quantity; // 折扣後的商品總價
			
			// 查詢商品圖片
			String imageUrl = null;
            Optional<ProductImage> productImageOptional = productImageRepository
                    .findByProduct_ProductidAndProductColor_ColorsidAndIsprimary(product.getProductid(), colorsId, true);
            if (productImageOptional.isPresent()) {
                imageUrl = productImageOptional.get().getImageurl();
            }
			
			// 加入訂單詳細資訊
            JSONObject itemDetail = new JSONObject();
            itemDetail.put("productId", product.getProductid());
            itemDetail.put("productName", product.getProductname());
            itemDetail.put("colorsId", cart.getProductColor().getColorsid());
            itemDetail.put("productColor", cart.getProductColor().getColorname());
            itemDetail.put("productSpecs", "寬" + product.getWidth() + " 高" + product.getHeight() + " 深" + product.getDepth());
            itemDetail.put("imageUrl", imageUrl); // 商品圖片（若無圖片則為 null）
            itemDetail.put("quantity", quantity);
            itemDetail.put("price", discountPrice);
            
            itemDetails.put(itemDetail);
		}

		int payableAmount = totalPrice + shippingFee; // 計算總應付金額

		// 返回結果
		JSONObject summary = new JSONObject();
		summary.put("itemsCount", itemsCount); // 已選商品數
		summary.put("totalPrice", totalPrice); // 商品總價
		summary.put("shippingFee", shipRateRepository.findAll()); // 在前端抓取運費費率
		summary.put("payableAmount", payableAmount); // 總應付金額	
		summary.put("itemDetails", itemDetails); // 加入訂單詳細資訊

		return summary;
	}

}

