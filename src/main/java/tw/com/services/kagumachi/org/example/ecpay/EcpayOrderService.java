package tw.com.services.kagumachi.org.example.ecpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import tw.com.services.kagumachi.ecpay.payment.integration.AllInOne;
import tw.com.services.kagumachi.ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.stereotype.Service;
import tw.com.services.kagumachi.model.Product;
import tw.com.services.kagumachi.model.ProductColor;
import tw.com.services.kagumachi.repository.ProductColorRepository;
import tw.com.services.kagumachi.repository.ProductRepository;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class EcpayOrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductColorRepository productColorRepository;

    public String ecpayCheckout(int memberid, EcpayDTO ecpayDTO) {
        AllInOne all = new AllInOne("");
        AioCheckOutALL obj = new AioCheckOutALL();

        // 金流單號20碼: "KG" + memberid + "長度10的亂數"
        String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        String merchantTradeNo = "KG" + memberid + uuId;
        obj.setMerchantTradeNo(merchantTradeNo);

        // 時間
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
        obj.setMerchantTradeDate(ft.format(dNow));

        // 總金額
        obj.setTotalAmount(ecpayDTO.getTotalPrice() + "");
        obj.setTradeDesc("test Description");

        // 商品明細
        StringBuilder itemName = new StringBuilder();
        for (int i = 0; i < ecpayDTO.getOrderDetailDTOs().size(); i++) {
            int productId = ecpayDTO.getOrderDetailDTOs().get(i).getProductId();
            itemName.append("#productid: " + productId + ", ");
//            Optional<Product> product = productRepository.findById(productId);
//            if (product.isPresent()) {
//                itemName.append(product.get().getProductname()).append("-");
//            }

            int colorsId = ecpayDTO.getOrderDetailDTOs().get(i).getColorsId();
            itemName.append("colorsid: " + colorsId + " ");
//            Optional<ProductColor> productColor = productColorRepository.findById(colorsId);
//            if (productColor.isPresent()) {
//                itemName.append(productColor.get().getColorname());
//            }

            int quantity = ecpayDTO.getOrderDetailDTOs().get(i).getQuantity();
            itemName.append("x").append(quantity).append(" ");
        }
        obj.setItemName(itemName.toString());


        // 交易結果回傳網址
        // 先在terminal下指令：ngrok http --url=engaging-fly-early.ngrok-free.app 8080
        obj.setReturnURL("<https://engaging-fly-early.ngrok-free.app>");

        // 商店轉跳網址 (注意port號目前寫死5173)
        obj.setClientBackURL("http://localhost:5173/Kagumachi/CartStep4");

        obj.setNeedExtraPaidInfo("N");

        String form = all.aioCheckOut(obj, null);

        return form;
    }
}

