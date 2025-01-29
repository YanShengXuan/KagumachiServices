package tw.com.services.kagumachi.dto;

import tw.com.services.kagumachi.model.Order;

public class OrderDeliveryDataDto {
	private String shippingmethod;
	private String ordercity;
	private String recipient;
	private String phone;
	private String district;
	private String address;
	public String getShippingmethod() {
		return shippingmethod;
	}
	public void setShippingmethod(String shippingmethod) {
		this.shippingmethod = shippingmethod;
	}
	public String getOrdercity() {
		return ordercity;
	}
	public void setOrdercity(String ordercity) {
		this.ordercity = ordercity;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
