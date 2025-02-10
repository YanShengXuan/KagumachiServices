package tw.com.services.kagumachi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.chat.model.Message;
import tw.com.services.kagumachi.chat.repository.MessageRepository;
import tw.com.services.kagumachi.model.MainCategory;
import tw.com.services.kagumachi.model.Member;
import tw.com.services.kagumachi.model.OrderDetail;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductColor;
import tw.com.services.kagumachi.model.Sales;
import tw.com.services.kagumachi.model.Suppliers;
import tw.com.services.kagumachi.repository.MainCategoryRepository;
import tw.com.services.kagumachi.repository.MemberRepository;
import tw.com.services.kagumachi.repository.OrderDetailRepository;
import tw.com.services.kagumachi.repository.OrderRepository;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductRepository;
import tw.com.services.kagumachi.repository.SalesRepository;
import tw.com.services.kagumachi.repository.SuppliersRepository;
import tw.com.services.kagumachi.service.SuppliersService;

import org.springframework.web.bind.annotation.RequestParam;
import tw.com.services.kagumachi.repository.SalesRepository;
import tw.com.services.kagumachi.repository.SuppliersRepository;

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
	
	@Autowired
	private SuppliersRepository suppliersRepository;

	@Autowired
	private ProductColorRepository productColorRepository;
	
	@Autowired
	private SalesRepository salesRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	//銷售最多
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

	//新增會員人數
	@GetMapping("/member")
	public String getMember() {
	    List<Member> members = memberRepository.findAll();
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    
	    Map<Integer, Integer> monthCountMap = new HashMap<>();

	    
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
	
	//優惠活動
	@GetMapping("/getsales")
    public String getSales() {
		 List<MainCategory> maincategorys = mainCategoryRepository.findAll();
		 List<Sales> sales = salesRepository.findAll();
		 JSONArray jsonArray = new JSONArray();
		 
		 for(MainCategory maincategory : maincategorys) {
			 if(maincategory.getSales().getSalesid()!=1) {
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("salesid",maincategory.getSales().getSalesid());
				 jsonObject.put("categoryname",maincategory.getCategoryname());				 
				 for(Sales sale : sales) {
					 if(maincategory.getSales().getSalesid() == sale.getSalesid()) {
						 jsonObject.put("salesdesc",sale.getSalesdesc()); 
						 jsonArray.put(jsonObject);	
					 }
				 }
			 }
		 }
		 return jsonArray.toString();
	}

	 //目前訂單統計
	 @GetMapping("/quantity")
	 public ResponseEntity<Map<String, Integer>> getQuantity() {
		List<OrderDetail> orderdetails = orderDetailRepository.findAll();
	    List<Product> products = productRepository.findAll();

	        // 初始化 Map，確保 1 到 6 的值都存在，並預設為 0
	     Map<Integer, Integer> qMap = new HashMap<>();
	     for (int i = 1; i <= 6; i++) {
	           qMap.put(i, 0);
	      }

	        // 統計出現次數
	        for (OrderDetail orderdetail : orderdetails) {
	            for (Product product : products) {
	                if (product.getProductid().equals(orderdetail.getProduct().getProductid())) {
	                    int mainCategoryId = product.getMainCategory().getMaincategoryid();
	                    qMap.put(mainCategoryId, qMap.getOrDefault(mainCategoryId, 0) + 1);
	                }
	            }
	        }
	        Map<String, Integer> result = qMap.entrySet()
	                .stream()
	                .collect(Collectors.toMap(
	                        entry -> String.valueOf(entry.getKey()), // 將鍵轉為字串
	                        Map.Entry::getValue
	                ));
	        // 自動轉換 qMap 為 JSON 返回
	        return ResponseEntity.ok(result);
	    }
	 
	 //補獲商品
	 @GetMapping("/getstock")
	 public String getallStock() {
	 	List<ProductColor> productColors = productColorRepository.findAll();
	 	List<Product> products = productRepository.findAll();
	 	List<Suppliers> suppliers = suppliersRepository.findAll();
	 		
	 	JSONArray jsonArray = new JSONArray();
	 		for(ProductColor productColor: productColors) {
	 			if((productColor.getStock())-(productColor.getMinstock())<(productColor.getMinstock())) {
	 				JSONObject jsonObject = new JSONObject();
	 				double m = productColor.getStock()-productColor.getMinstock();
	 				if(m==0) {
	 					continue;
	 				}else {
	 					m = m * (-1);
	 				}		
					jsonObject.put("productid",productColor.getProduct().getProductid());
					jsonObject.put("colorsid",productColor.getColorsid());
					jsonObject.put("stock",productColor.getStock());
					jsonObject.put("minstock",m);
					for(Product product:products) {
						for(Suppliers supplier:suppliers) {
							jsonObject.put("name",productColor.getProduct().getSupplier().getName());
							jsonObject.put("phone",productColor.getProduct().getSupplier().getPhone());
							}
						}
					jsonArray.put(jsonObject);	
					}
					
	 			}
	 			return jsonArray.toString();
	 		}
	 
	 //客服
	 @GetMapping("/getmessage")
	 public String getMessage() {
	 			JSONArray jsonArray = new JSONArray();
	 			
	 			List<Message> messages = messageRepository.findAll();
	 			for(Message message:messages) {
	 				JSONObject jsonObject = new JSONObject();
	 				System.out.println(message.isIsbackread());
	 				jsonObject.put("isbackread",message.isIsbackread());
	 				jsonArray.put(jsonObject);	
	 			}
	 			return jsonArray.toString();
	 		}
	 		
	 //圖表
	 @GetMapping("/getpic")
	 public String  getAllPic() {
	        List<OrderDetail> orderdetails = orderDetailRepository.findAll();
	        JSONObject jsonObject = new JSONObject();
	        
	        int total = 0;
	        int cost = 0;
	        
	        Map<Integer, Integer> categoryAmounts = new HashMap<>();
	        for (int i = 1; i <= 6; i++) {
	            categoryAmounts.put(i, 0); // 初始化每個大類別的金額為 0
	        }

	        for (OrderDetail orderdetail : orderdetails) {
	            int discountPrice = orderdetail.getProduct().getDiscountprice();
	            int quantity = orderdetail.getQuantity();
	            int productCost = orderdetail.getProduct().getProductcost();
	            int categoryId = orderdetail.getProduct().getMainCategory().getMaincategoryid();

	            // 計算總收入和總成本
	            total += discountPrice * quantity;
	            cost += productCost * quantity;

	            
	            if (categoryAmounts.containsKey(categoryId)) {
	                categoryAmounts.put(categoryId, categoryAmounts.get(categoryId) + (discountPrice * quantity));
	            }
	        }

	        int fin = total - cost;
        
	        jsonObject.put("total", total);
	        jsonObject.put("cost", cost);
	        jsonObject.put("fin", fin);
	        jsonObject.put("categoryAmounts", categoryAmounts); // 將各大類金額回傳

	        return jsonObject.toString();
	 }
}


