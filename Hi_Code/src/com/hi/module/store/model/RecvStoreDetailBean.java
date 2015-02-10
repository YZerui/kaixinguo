package com.hi.module.store.model;

import java.util.ArrayList;

/**
 * 封装店家详情信息的Bean
 * @author MM_Zerui
 *
 */
public class RecvStoreDetailBean {
	//店名
	private String name;
	//店家联系方式
	private String phone;
	//店家地址
	private String address;
	//店家照片墙
	private ArrayList<String> photos;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<String> getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList<String> photos) {
		this.photos = photos;
	}
}
