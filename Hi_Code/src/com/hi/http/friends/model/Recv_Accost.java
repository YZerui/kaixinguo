package com.hi.http.friends.model;

/**
 * 搭讪
 * @author MM_Zerui
 *
 */
public class Recv_Accost {
	private long id;//消息ID
	private String time;//消息发送时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
