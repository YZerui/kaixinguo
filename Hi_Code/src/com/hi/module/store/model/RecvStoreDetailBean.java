package com.hi.module.store.model;

import java.util.ArrayList;

/**
 * ��װ���������Ϣ��Bean
 * @author MM_Zerui
 *
 */
public class RecvStoreDetailBean {
	//����
	private String name;
	//�����ϵ��ʽ
	private String phone;
	//��ҵ�ַ
	private String address;
	//�����Ƭǽ
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
