package com.hi.dao.model;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.view.annotation.PreferenceInject;

/**
 * 记录我的朋友信息的Bean
 * @author MM_Zerui
 *
 */
@Table(name="t_myFriends",execAfterTableCreated = "CREATE INDEX index_myfriend ON t_myFriends(mid)")
public class T_MyFriends extends EntityBase{
	@Column(column="birthday")
	private String birthDay;//对方生日
	
	@Column(column="currentState")
    private String currentState;  //当前状态
	
	@Column(column="fid")
    private String fid;  //对方ID
	
	@Column(column="head")
    private String head; //对方头像
	
	@Column(column="location")
    private String location; //对方位置
	
	@Column(column="mid")
    private String mid;//本人ID
	
	@Column(column="nickName")
    private String nickName;//本人昵称
	
	@Column(column="sex")
    private String sex;//性别
	
	@Column(column="type")
    private int type;//关系类型
	
	@Column(column="accost_in_num")
    private int accost_in_num;//被搭讪次数
	
	@Column(column="accost_out_num")
    private int accost_out_num;//主动搭讪次数

	@Column(column="accost_num")
	private int accost_num;
	
	@Column(column="accost_time")
	private String accost_time;
	
	@Column(column="avosid")
	private String avosid;
	
	@Column(column="driverType")
	private String driverType;
	
	
	
	public String getAvosid() {
		return avosid;
	}

	public void setAvosid(String avosid) {
		this.avosid = avosid;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public int getAccost_num() {
		return accost_num;
	}

	public void setAccost_num(int accost_num) {
		this.accost_num = accost_num;
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

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}



	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAccost_in_num() {
		return accost_in_num;
	}

	public void setAccost_in_num(int accost_in_num) {
		this.accost_in_num = accost_in_num;
	}

	public int getAccost_out_num() {
		return accost_out_num;
	}

	public void setAccost_out_num(int accost_out_num) {
		this.accost_out_num = accost_out_num;
	}

	public String getAccost_time() {
		return accost_time;
	}

	public void setAccost_time(String accost_time) {
		this.accost_time = accost_time;
	}
	
	
	
}
