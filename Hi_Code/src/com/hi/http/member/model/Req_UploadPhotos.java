package com.hi.http.member.model;

/**
 * 照片墙上传请求参数
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
