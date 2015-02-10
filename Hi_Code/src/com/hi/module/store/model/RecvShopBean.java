package com.hi.module.store.model;

public class RecvShopBean {
	private String id;
	private String address;
	private String friends;
	private String logo;
	private String name;
	private String sms;
	private String coordinates;
	private String islove;
	private String coupons;//优惠劵
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCoupons() {
		return coupons;
	}
	public void setCoupons(String coupons) {
		this.coupons = coupons;
	}
	public String getIslove() {
		return islove;
	}
	public void setIslove(String islove) {
		this.islove = islove;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if(address==null||address.equals("")){
			address="未知地址";
		}
		this.address = address;
	}
	public String getFriends() {
		return friends;
	}
	public void setFriends(String friends) {
		if(friends==null||friends.equals("")){
			friends="0";
		}
		this.friends = friends;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name==null||name.equals("")){
			name="未知店铺";
		}
		this.name = name;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
}
