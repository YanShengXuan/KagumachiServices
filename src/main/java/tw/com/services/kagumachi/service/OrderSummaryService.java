package tw.com.services.kagumachi.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.model.Cart;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.repository.CartRepository;
import tw.com.services.kagumachi.repository.ShipRateRepository;

@Service
public class OrderSummaryService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ShipRateRepository shipRateRepository;

	public JSONObject getOrderSummary(Integer memberid) {
		System.out.println(memberid);
		List<Cart> carts = cartRepository.findByMember_MemberidAndIspurchase(memberid, true);
		System.out.println(carts);

		int itemsCount = 0; // 總商品數量
		int totalPrice = 0; // 折扣後的商品總價
		int shippingFee = 0; // 預設運費

		for (Cart cart : carts) {
			Product product = cart.getProduct();
			int quantity = cart.getQuantity();

			// 確保價格數據有效（避免 null）
			Integer discountPrice = product.getDiscountprice();
//			Integer unitPrice = product.getUnitprice();

			// 計算總數量和總價
			itemsCount += quantity; // 總商品數量
			totalPrice += discountPrice * quantity; // 折扣後的商品總價
		}

		// 計算總應付金額
		int payableAmount = totalPrice + shippingFee;

		// 返回結果
		JSONObject summary = new JSONObject();
		summary.put("itemsCount", itemsCount);
		summary.put("totalPrice", totalPrice);
		summary.put("shippingFee", shipRateRepository.findAll()); // 在前端抓取運費費率
		summary.put("payableAmount", payableAmount); // 總應付金額	

		return summary;
	}

}

