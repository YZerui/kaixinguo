package com.hi.http.member.model;

/**
 * ��Ƭǽ�ϴ��������
 * @author MM_Zerui
 *
 */
public class Req_UploadPhotos {
	private String uid;
	private String location;
	private String photo;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
