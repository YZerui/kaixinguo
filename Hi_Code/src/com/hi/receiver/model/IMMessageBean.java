package com.hi.receiver.model;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.avos.avoscloud.AVMessage;
import com.exception.utils.P;
import com.format.utils.Md5Util;
import com.hi.common.http.E_Http_SendState;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.msg.service.ChatUtils;
import com.hi.utils.DeviceUtils;
import com.hi.utils.ParseJson;

/**
 * 即时通讯的数据类型
 * 
 * @author MM_Zerui
 * 
 */
public class IMMessageBean {
	/**
	 * 消息类型
	 * 
	 * @author MM_Zerui
	 * 
	 */
	public enum Enum_IM_MsgType {
		UNKNOW(-1), ACCOST(0);
		private int value;

		private Enum_IM_MsgType(int value) {
			// TODO Auto-generated constructor stub
			this.value = value;
		}

		public int value() {
			return value;
		}

		public static Enum_IM_MsgType get(int value) {
			switch (value) {
			case 0:
				return ACCOST;
			default:
				return UNKNOW;
			}
		}
	}

	/**
	 * 消息内容格式
	 * 
	 * @author MM_Zerui
	 * 
	 */
	public enum Enum_IM_MsgFormat {
		UNKNOW(-1), TEXT(0), IMAGE(1), RADIO(2), LOCATION(3);
		private int value;

		private Enum_IM_MsgFormat(int value) {
			// TODO Auto-generated constructor stub
			this.value = value;
		}

		public int value() {
			return value;
		}

		public static Enum_IM_MsgFormat get(int value) {
			switch (value) {
			case 0:
				return TEXT;
			case 1:
				return IMAGE;
			case 2:
				return RADIO;
			case 3:
				return LOCATION;
			default:
				return UNKNOW;
			}
		}
	}

	/**
	 * 封装到Json传递的bean
	 * 
	 * @author MM_Zerui
	 * 
	 */
	public static class IMMessage {
		private String u_sendID;
		private String u_head;
		private String u_name;
		private String m_content;
		private String u_animID;
		private String objectId;
		// private long m_timestamp;
		private int m_type;
		private int m_formate;
        private AVMessage internalMessage;
        public AVMessage getInternalMessage() {
            return internalMessage;
        }

        public void setInternalMessage(AVMessage internalMessage) {
            this.internalMessage = internalMessage;
        }
        public T_IMsg toT_IMsg(int identity, int msgType, int imageUrlMode) {
            T_IMsg msgBean = new T_IMsg();
            msgBean.setObjectId(getObjectId());
            msgBean.setHead(getU_head());
            msgBean.setIdentity(identity);// 身份为对方
            msgBean.setMid(Dao_SelfIfo.getInstance().getMid());
            msgBean.setMsg(getM_content());
            msgBean.setMsgFormat(getM_formate());
            msgBean.setMsgType(msgType);
            msgBean.setTime(getTimestamp());
            msgBean.setName(getU_name());
            msgBean.setUid(getU_sendID());
            msgBean.setImageUrlMode(imageUrlMode);
            msgBean.setWifiMac(DeviceUtils.getWifiMac());
            msgBean.setConvid(ChatUtils.convid(msgBean.getMid(), msgBean.getUid()));
            //对接收到的消息在本地设定发送状态为 “到达”
            msgBean.setSendState(E_Http_SendState.DELIVERED.value());
            return msgBean;
        }
        public long getTimestamp() {
            return internalMessage.getTimestamp();
        }
		public String getU_animID() {
			return u_animID;
		}

		public void setU_animID(String u_animID) {
			this.u_animID = u_animID;
		}

		public String getObjectId() {
			return objectId;
		}

		public void setObjectId(String objectId) {
			this.objectId = objectId;
		}

		public String getU_sendID() {
			return u_sendID;
		}

		public void setU_sendID(String u_sendID) {
			this.u_sendID = u_sendID;
		}

		public String getU_head() {
			return u_head;
		}

		public void setU_head(String u_head) {
			this.u_head = u_head;
		}

		public String getU_name() {
			return u_name;
		}

		public void setU_name(String u_name) {
			this.u_name = u_name;
		}

		public String getM_content() {
			return m_content;
		}

		public void setM_content(String m_content) {
			this.m_content = m_content;
		}

		// public long getM_timestamp() {
		// return m_timestamp;
		// }
		//
		// public void setM_timestamp(long m_timestamp) {
		// this.m_timestamp = m_timestamp;
		// }

		public int getM_type() {
			return m_type;
		}

		public void setM_type(int m_type) {
			this.m_type = m_type;
		}

		public int getM_formate() {
			return m_formate;
		}

		public void setM_formate(int m_formate) {
			this.m_formate = m_formate;
		}

	}

	private AVMessage avmMsg = new AVMessage();
	private IMMessage msgBean = new IMMessage();
	private List<String> peerIds;

	public IMMessageBean setContent(String m_content) {
		msgBean.setM_content(m_content);
		return this;
	}

	public String getContent() {
		return msgBean.getM_content();
	}

	public IMMessageBean setMsgType(Enum_IM_MsgType msgType) {
		msgBean.setM_type(msgType.value);
		return this;
	}

	public IMMessageBean setFormat(Enum_IM_MsgFormat msgFormat) {
		msgBean.setM_formate(msgFormat.value);
		return this;
	}

	public IMMessageBean setAnimId(String animId) {
		msgBean.setU_animID(animId);
		return this;
	}

	public IMMessageBean setObjectId(String objectId) {
		msgBean.setObjectId(objectId);
		return this;
	}

	/**
	 * 设定接收信息的用户ID
	 * 
	 * @param peerIds
	 * @return
	 */
	public IMMessageBean setToPeerIds(String... peerIds) {
		this.peerIds = Arrays.asList(peerIds);
		return this;
	}

	public static IMMessage fromAVMessage(AVMessage msg) {
		try {
			String jsonContent = msg.getMessage();
			JSONObject jsonObject = new JSONObject(jsonContent);
			IMMessage iMsg = ParseJson.parseJsonToBean(jsonObject,
					IMMessage.class);
			// iMsg.setM_timestamp(msg.getTimestamp());
			return iMsg;
		} catch (Exception e) {
			// TODO: handle exception
			P.v("解析IM收到消息出错:" + e.getMessage());
		}
		return null;

	}

	/**
	 * 得到需要发送的指定格式的IM消息
	 * 
	 * @return
	 */
	public AVMessage makeAVMessage() {
		try {
			// 自动生成的参数

			msgBean.setU_head(Dao_SelfIfo.getInstance().getHead());
			msgBean.setU_sendID(Dao_SelfIfo.getInstance().getMid());
			msgBean.setU_name(Dao_SelfIfo.getInstance().getNickName());
//			JSONObject jsonObject = new JSONObject(
//					com.format.utils.FormatUtils.convertBeanToMap(msgBean));
			JSONObject jsonObject=parseAVMessage(msgBean);
			avmMsg.setToPeerIds(peerIds);
			avmMsg.setRequestReceipt(true);// 开启消息到达回执
			avmMsg.setMessage(jsonObject.toString());
			return avmMsg;
		} catch (Exception e) {
			return null;
		}
	}

	private JSONObject parseAVMessage(IMMessage imMessage) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("u_head", msgBean.getU_head());
			jsonObject.put("u_name", msgBean.getU_name());
			jsonObject.put("m_content", msgBean.getM_content());
			jsonObject.put("u_animID", msgBean.getU_animID());
			jsonObject.put("objectId", msgBean.getObjectId());
			jsonObject.put("m_type",msgBean.getM_type());
			jsonObject.put("m_formate", msgBean.getM_formate());
            jsonObject.put("u_sendID",Dao_SelfIfo.getInstance().getMid());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return jsonObject;
	}

}
