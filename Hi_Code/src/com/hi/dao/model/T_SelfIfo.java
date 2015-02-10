package com.hi.dao.model;

import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.utils.DBUtils;
import com.hi.utils.FormatUtils;
import com.hi.utils.XingZuo;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

////情况个人身份信息

@Table(name = "T_SelfIfo")
public class T_SelfIfo extends EntityBase {
	@Column(column = "mid")
	private String mid;

	@Column(column = "nickName")
	private String nickName;

	@Column(column = "head")
	private String head;

	@Column(column = "interest")
	private String interest;

	@Column(column = "currentState")
	private String currentState;

	@Column(column = "occupation")
	private String occupation;

	@Column(column = "phone")
	private String phone;

	@Column(column = "remark")
	private String remark;

	@Column(column = "sex")
	private String sex;

	@Column(column = "note")
	private String note;

	@Column(column = "birthDay")
	private String birthDay;

	@Column(column = "accostedEffe")
	private String accostedEffe;

	@Column(column = "locationEffe")
	private String locationEffe;

	@Column(column = "photos_1")
	private String photos_1;

	@Column(column = "photos_2")
	private String photos_2;

	@Column(column = "photos_3")
	private String photos_3;

	public String getPhotos_1() {
		return photos_1;
	}

	public void setPhotos_1(String photos_1) {
		this.photos_1 = photos_1;
	}

	public String getPhotos_2() {
		return photos_2;
	}

	public void setPhotos_2(String photos_2) {
		this.photos_2 = photos_2;
	}

	public String getPhotos_3() {
		return photos_3;
	}

	public void setPhotos_3(String photos_3) {
		this.photos_3 = photos_3;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getAccostedEffe() {
		return accostedEffe;
	}

	public void setAccostedEffe(String accostedEffe) {
		this.accostedEffe = accostedEffe;
	}

	public String getLocationEffe() {
		return locationEffe;
	}

	public void setLocationEffe(String locationEffe) {
		this.locationEffe = locationEffe;
	}

	public String getXingZuo() {
		String str = XingZuo
				.getXingZuo(Dao_SelfIfo.getInstance().getBirthDay());
		if (str == null || str.equals("") || str.length() == 0) {
			return "未知星座";
		}
		return str;
	}

	public String getAge() {
		try {
			int age = Integer.valueOf(FormatUtils.getDateTime_YEAR(FormatUtils
					.getCurrentDateValue()))
					- Integer.valueOf(FormatUtils.getDateTime_YEAR(Dao_SelfIfo
							.getInstance().getBirthDay()));
			return String.valueOf(age);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
