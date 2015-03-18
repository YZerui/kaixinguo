package com.hi.http.store.model;

/**
 * 商家列表
 * @author MM_Zerui
 *
 */
public class Recv_StoreList {
	private String address;
//	private String coordinates;
    private String distance;
	private String coupons;
	private String friends;
	private String id;
	private String islove;
    private String latAdd;
    private String longAdd;
	private String logo;
	private String name;
	private String phone;
    private String remark;
	private String sms;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLatAdd() {
        return latAdd;
    }

    public void setLatAdd(String latAdd) {
        this.latAdd = latAdd;
    }

    public String getLongAdd() {
        return longAdd;
    }

    public void setLongAdd(String longAdd) {
        this.longAdd = longAdd;
    }

    public String getCoupons() {
		return coupons;
	}
	public void setCoupons(String coupons) {
		this.coupons = coupons;
	}
	public String getFriends() {
		return friends;
	}
	public void setFriends(String friends) {
		this.friends = friends;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIslove() {
		return islove;
	}
	public void setIslove(String islove) {
		this.islove = islove;
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
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	
}
