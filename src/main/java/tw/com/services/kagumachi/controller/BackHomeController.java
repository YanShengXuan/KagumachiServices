package tw.com.services.kagumachi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.model.MainCategory;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.OrderDetail;
import tw.com.services.kagumachi.repository.MainCategoryRepository;
import tw.com.services.kagumachi.repository.MemberRepository;
import tw.com.services.kagumachi.repository.OrderDetailRepository;
import tw.com.services.kagumachi.repository.OrderRepository;

@RestController
@RequestMapping("/myback")
public class BackHomeController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MainCategoryRepository  mainCategoryRepository;

	@GetMapping()
	public String getproduct() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        Map<String, JSONObject> productMap = new HashMap<>();
        for (OrderDetail orderDetail : orderDetails) {
            String productname = orderDetail.getProduct().getProductname();
            if (productMap.containsKey(productname)) {
                JSONObject jsonObject = productMap.get(productname);
                int currentQuantity = jsonObject.getInt("quantity");
                jsonObject.put("quantity", currentQuantity + orderDetail.getQuantity());
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("productname", productname);
                jsonObject.put("quantity", orderDetail.getQuantity());
                productMap.put(productname, jsonObject);
            }
        }
//       JSONArray jsonArray = new JSONArray(productMap.values());
        List<JSONObject> productList = new ArrayList<>(productMap.values());

        productList.sort((o1, o2) -> Integer.compare(o2.getInt("quantity"), o1.getInt("quantity")));
        JSONArray jsonArray = new JSONArray(productList);
        
        System.out.println(productMap);
        return jsonArray.toString();
    }

	
	@GetMapping("/test")
	public String getMember() {
	    List<Member> members = memberRepository.findAll();
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    // 用来存储解析出的月份及其出现次数
	    Map<Integer, Integer> monthCountMap = new HashMap<>();

	    // 遍历成员，统计月份出现次数
	    for (Member member : members) {
	        String registrationDate = member.getRegistrationdate().toString();
	        LocalDate date = LocalDate.parse(registrationDate, dateFormatter); // 使用 LocalDate 解析
	        int month = date.getMonthValue(); // 提取月份
	        monthCountMap.put(month, monthCountMap.getOrDefault(month, 0) + 1); // 累加计数
	    }

	    // 按照月份的跨年顺序排序
	    List<Map.Entry<Integer, Integer>> sortedEntries = new ArrayList<>(monthCountMap.entrySet());
	    sortedEntries.sort((entry1, entry2) -> {
	        int m1 = entry1.getKey();
	        int m2 = entry2.getKey();
	        if (m1 >= 11 && m2 < 11) {
	            return -1; // 11 和 12 优先
	        } else if (m1 < 11 && m2 >= 11) {
	            return 1; // 其他月份排在后面
	        } else {
	            return Integer.compare(m1, m2); // 自然排序
	        }
	    });

	    // 转换回 Map 按照排序的结果返回
//	    Map<Integer, Integer> result = new LinkedHashMap<>();
//	    for (Map.Entry<Integer, Integer> entry : sortedEntries) {
//	        result.put(entry.getKey(), entry.getValue());
//	    }

//	    
//	    result.forEach((month, count) -> {
//	        System.out.println("Month: " + month + ", Count: " + count);
//	    });
//
//	    return result;
//	}
//}
	    JSONObject result = new JSONObject();
	    JSONArray jsonArray = new JSONArray();

	    for (Map.Entry<Integer, Integer> entry : sortedEntries) {
	        JSONObject monthData = new JSONObject();
	        monthData.put("month", entry.getKey());
	        monthData.put("count", entry.getValue());
	        jsonArray.put(monthData);
	    }

	    result.put("data", jsonArray);

	    
	    System.out.println(result.toString(4)); // 格式化输出 JSON（方便调试）

	    return result.toString(); // 返回 JSON 字符串
	}
	

}