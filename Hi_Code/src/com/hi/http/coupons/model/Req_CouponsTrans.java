package com.hi.http.coupons.model;

/**
 * ת���Ż݄�
 * @author MM_Zerui
 *
 */
public class Req_CouponsTrans {
	private String cid;//�Ż�ȯ��ӦID
	private String uid;//�û���Ψһ��ʶUUID:��¼�����ȡ����mid
	private String uids;//��ת�����û�ID(����û�ʹ��,�ŷָ�)
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUids() {
		return uids;
	}
	public void setUids(String uids) {
		this.uids = uids;
	}
	
}
