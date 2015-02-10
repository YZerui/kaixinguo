package com.hi.dao.model;

import com.hi.common.PARAMS;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/** 
 * 封装通讯录信息
 * @author MM_Zerui
 *
 */
@Table(name="t_contacts",execAfterTableCreated = "CREATE INDEX index_contacts ON t_contacts(phone)")
public class T_Contacts extends EntityBase{
		
	@Column(column="name")
	private String name;
	
	@Column(column="phone")
	private String phone;
	
	@Column(column="ifRegister",defaultValue=PARAMS.CONTACT_UNREGISTER)
	private int ifRegister;

	@Column(column="uid")
	private String uid;
	
	@Column(column="firstLetter")
	private String firstLetter;

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
	public int getIfRegister() {
		return ifRegister;
	}
	public void setIfRegister(int ifRegister) {
		this.ifRegister = ifRegister;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFirstLetter() {
		return firstLetter;
	}
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	
	
}
