package com.hi.http.member.model;

import java.text.ParsePosition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.os.Build;
import com.exception.utils.P;
import com.hi.common.recv.E_Recv_StroeIfo;
import com.hi.common.recv.E_Recv_UserIfo;
import com.hi.http.store.model.Recv_StoreIfo;

/**
 * 接收的用户信息
 * 
 * @author MM_Zerui
 * 
 */
public class Recv_UserIfo {
	private String accostTimes;
	private String accostedEffe;
	private String birthDay;
	private String currentState;
	private String head;
	private String interest;
	private String locationEffe;
	private String mid;
	private String nickName;
	private String note;
	private String occupation;
	private String openID;
	private String openType;
	private String phone;
	private String photos;
	private String photos_1;//照片墙图1
	private String photos_2;//照片墙图2
	private String photos_3;//照片墙图3
	private String remark;
	private String sex;
	private String state;
	private String type;

	public static Recv_UserIfo parse(String jsonStr) throws Exception {
		try {
			JSONObject object = new JSONObject(jsonStr);
			JSONObject jsonObject = object.getJSONObject("data");
			Recv_UserIfo recvBean = new Recv_UserIfo();
			recvBean.setAccostedEffe(jsonObject.getString(E_Recv_UserIfo.accostedEffe.name()));
			recvBean.setAccostTimes(jsonObject.getString(E_Recv_UserIfo.accostTimes.name()));
			recvBean.setBirthDay(jsonObject.getString(E_Recv_UserIfo.birthDay.name()));
			recvBean.setCurrentState(jsonObject.getString(E_Recv_UserIfo.currentState.name()));
			recvBean.setHead(jsonObject.getString(E_Recv_UserIfo.head.name()));
			recvBean.setInterest(jsonObject.getString(E_Recv_UserIfo.interest.name()));
			recvBean.setLocationEffe(jsonObject.getString(E_Recv_UserIfo.locationEffe.name()));
			recvBean.setMid(jsonObject.getString(E_Recv_UserIfo.mid.name()));
			recvBean.setNickName(jsonObject.getString(E_Recv_UserIfo.nickName.name()));
			recvBean.setNote(jsonObject.getString(E_Recv_UserIfo.note.name()));
			recvBean.setOccupation(jsonObject.getString(E_Recv_UserIfo.occupation.name()));
			recvBean.setOpenID(jsonObject.getString(E_Recv_UserIfo.openID.name()));
			recvBean.setOpenType(jsonObject.getString(E_Recv_UserIfo.openType.name()));
			recvBean.setPhone(jsonObject.getString(E_Recv_UserIfo.phone.name()));
			recvBean.setPhotos(jsonObject.getString(E_Recv_UserIfo.photos.name()));
			recvBean.setRemark(jsonObject.getString(E_Recv_UserIfo.remark.name()));
			recvBean.setSex(jsonObject.getString(E_Recv_UserIfo.sex.name()));
			recvBean.setState(jsonObject.getString(E_Recv_UserIfo.state.name()));
			recvBean.setType(jsonObject.getString(E_Recv_UserIfo.type.name()));
			parsePhotos(recvBean,recvBean.getPhotos());
			return recvBean;
		} catch (Exception e) {
			// TODO: handle exception
			P.v("解析用户信息出现错误:"+e.toString());
			throw e;
		}
	}
	/**
	 * 解析相片信息
	 * @param jsonStr
	 */
	@TargetApi(19)
	public static void parsePhotos(Recv_UserIfo recvBean,String jsonStr) {
		try {
			JSONArray array=new JSONArray(jsonStr);
			for(int i=0;i<array.length();i++){
				JSONObject item=array.getJSONObject(i);
				switch (i) {
				case 0:
					recvBean.setPhotos_1(item.getString("url"));
					break;
				case 1:
					recvBean.setPhotos_2(item.getString("url"));
					break;
				case 2:
					recvBean.setPhotos_3(item.getString("url"));
					break;
				default:
					break;
				}
				
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getAccostTimes() {
		return accostTimes;
	}

	public void setAccostTimes(String accostTimes) {
		this.accostTimes = accostTimes;
	}

	public String getAccostedEffe() {
		return accostedEffe;
	}

	public void setAccostedEffe(String accostedEffe) {
		this.accostedEffe = accostedEffe;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
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

	public String getLocationEffe() {
		return locationEffe;
	}

	public void setLocationEffe(String locationEffe) {
		this.locationEffe = locationEffe;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
