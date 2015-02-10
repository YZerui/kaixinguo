package com.hi.http.store.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exception.utils.P;
import com.hi.common.recv.E_Recv_StroeIfo;

public class Recv_StoreIfo {
	private String address;
	private String balance;
	private String bid;
	private String businessType;
	private String contact;
	private String coordinates;
	private String createTime;
	private String createTimeDate;
	private String descHtml;
	private String fixPhone;
	private String logo;
	private String[] logos;//’’∆¨«Ω
	private String name;
	private String password;
	private String phone;
	private String photos;
	private String remark;
	private String serviceTime;
	private String serviceTimeDate;
	private String spreadCode;
	private String state;
	private String stateName;

	public Recv_StoreIfo parse(String jsonStr) throws Exception {
		try {
			JSONObject object = new JSONObject(jsonStr);
			JSONObject jsonObject = object.getJSONObject("data");
			Recv_StoreIfo recvBean = new Recv_StoreIfo();
			recvBean.setAddress(jsonObject.getString(E_Recv_StroeIfo.address.name()));
			recvBean.setBalance(jsonObject.getString(E_Recv_StroeIfo.balance.name()));
			recvBean.setBid(jsonObject.getString(E_Recv_StroeIfo.bid.name()));
			recvBean.setBusinessType(jsonObject.getString(E_Recv_StroeIfo.businessType.name()));
			recvBean.setContact(jsonObject.getString(E_Recv_StroeIfo.contact.name()));
			recvBean.setCoordinates(jsonObject.getString(E_Recv_StroeIfo.coordinates.name()));
			recvBean.setCreateTime(jsonObject.getString(E_Recv_StroeIfo.createTime.name()));
			recvBean.setCreateTimeDate(jsonObject.getString(E_Recv_StroeIfo.createTimeDate.name()));
			recvBean.setDescHtml(jsonObject.getString(E_Recv_StroeIfo.descHtml.name()));
			recvBean.setFixPhone(jsonObject.getString(E_Recv_StroeIfo.fixPhone.name()));
			recvBean.setLogo(jsonObject.getString(E_Recv_StroeIfo.logo.name()));
//			recvBean.setLogo_1(jsonObject.getString(E_Recv_StroeIfo.logo_1.name()));
//			recvBean.setLogo_2(jsonObject.getString(E_Recv_StroeIfo.logo_2.name()));
//			recvBean.setLogo_3(jsonObject.getString(E_Recv_StroeIfo.logo_3.name()));
			recvBean.setName(jsonObject.getString(E_Recv_StroeIfo.name.name()));
			recvBean.setPassword(jsonObject.getString(E_Recv_StroeIfo.password.name()));
			recvBean.setPhone(jsonObject.getString(E_Recv_StroeIfo.phone.name()));
			recvBean.setPhotos(jsonObject.getString(E_Recv_StroeIfo.photos.name()));
			recvBean.setRemark(jsonObject.getString(E_Recv_StroeIfo.remark.name()));
			recvBean.setServiceTime(jsonObject.getString(E_Recv_StroeIfo.serviceTime.name()));
			recvBean.setServiceTimeDate(jsonObject.getString(E_Recv_StroeIfo.serviceTimeDate.name()));
			recvBean.setSpreadCode(jsonObject.getString(E_Recv_StroeIfo.spreadCode.name()));
			recvBean.setState(jsonObject.getString(E_Recv_StroeIfo.state.name()));
			recvBean.setStateName(jsonObject.getString(E_Recv_StroeIfo.stateName.name()));
			//ªÒ»°’’∆¨«Ω
			recvBean.setLogos(getLogos(recvBean.getPhotos()));
			return recvBean;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			P.v("Ω‚ŒˆµÍº“œÍœ∏–≈œ¢¥ÌŒÛ:" + e1.toString());
			throw e1;
			
		}
	}

	public String[] getLogos(String jsonArray){
		
		try {
			JSONArray array=new JSONArray(jsonArray);
			logos=new String[array.length()];
			for(int i=0;i<array.length();i++){
				JSONObject object=(JSONObject) array.get(i);
				logos[i]=object.getString("url");
			}
			return logos;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] getLogos() {
		return logos;
	}

	public void setLogos(String[] logos) {
		this.logos = logos;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeDate() {
		return createTimeDate;
	}

	public void setCreateTimeDate(String createTimeDate) {
		this.createTimeDate = createTimeDate;
	}

	public String getDescHtml() {
		return descHtml;
	}

	public void setDescHtml(String descHtml) {
		this.descHtml = descHtml;
	}

	public String getFixPhone() {
		return fixPhone;
	}

	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getServiceTimeDate() {
		return serviceTimeDate;
	}

	public void setServiceTimeDate(String serviceTimeDate) {
		this.serviceTimeDate = serviceTimeDate;
	}

	public String getSpreadCode() {
		return spreadCode;
	}

	public void setSpreadCode(String spreadCode) {
		this.spreadCode = spreadCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
