package tw.com.services.kagumachi.org.example.ecpay;

import tw.com.services.kagumachi.dto.OrderDetailDTO;

import java.util.List;

public class EcpayDTO {
    private int totalPrice;
    private List<OrderDetailDTO> orderDetailDTOs;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetailDTO> getOrderDetailDTOs() {
        return orderDetailDTOs;
    }

    public void setOrderDetailDTOs(List<OrderDetailDTO> orderDetailDTOs) {
        this.orderDetailDTOs = orderDetailDTOs;
    }

    @Override
    public String toString() {
        return "EcpayDTO{" +
                "totalPrice=" + totalPrice +
                ", orderDetailDTOs=" + orderDetailDTOs +
                '}';
    }
}
