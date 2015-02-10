package com.hi.dao.model;

import java.io.Serializable;

import com.hi.utils.FormatUtils;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

@Table(name = "T_Coupons")
public class T_Coupons extends EntityBase implements Serializable{

	@Column(column = "total")
	private String total;

	@Column(column = "logo")
	private String logo;

	@Column(column = "phone")
	private String phone;

	@Column(column = "aloneEffe")
	private String aloneEffe;

	@Column(column = "image")
	private String image;

	@Column(column = "cid")
	private String cid;

	@Column(column = "content")
	private String content;

	@Column(column = "title")
	private String title;

	@Column(column = "forwardEffe")
	private String forwardEffe;

	@Column(column = "address")
	private String address;

	@Column(column = "name")
	private String name;

	@Column(column = "bid")
	private String bid;

	@Column(column = "coordinates")
	private String coordinates;

	@Column(column = "useTime")
	private String useTime;

	@Column(column = "intervalTime", defaultValue = "600")
	private int intervalTime;
	
	@Column(column="isUsing",defaultValue="0")
	private int isUsing;
	
	@Column(column = "isUse", defaultValue = "0")
	private int isUse;

	@Column(column = "mid")
	private String mid;

	
	public int getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAloneEffe() {
		return aloneEffe;
	}

	public void setAloneEffe(String aloneEffe) {
		this.aloneEffe = aloneEffe;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForwardEffe() {
		return forwardEffe;
	}

	public void setForwardEffe(String forwardEffe) {
		this.forwardEffe = forwardEffe;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	/**
	 * 得到剩余可用时间
	 * 
	 * @备注 剩余可用时间 rTime=(使用时间uTime+缓冲时间iTime)-当前时间cTime
	 * @return
	 */
	public long getRemainTime() {
		try {
			return intervalTime*1000 + Long.valueOf(useTime)
					- FormatUtils.getCurrentDateValue_long();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	/**
	 * 获取使用的截止时间
	 * 
	 * @return
	 */
	public long getEndTime() {
		try {
			return intervalTime*1000 + Long.valueOf(useTime);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

}
