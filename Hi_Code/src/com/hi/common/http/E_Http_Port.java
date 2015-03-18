package com.hi.common.http;

/**
 * �ӿ�
 * @author MM_Zerui
 *
 */
public enum E_Http_Port {
	/**ͨ��**/
	COM_MEMBER("/member/find.action"),
	COM_BUSINESS("/business/find.action"),
	COM_ACTIVITY("/activity/find.action"),
	/*********************ȫ��**************************/
	//��������
	ABOUT_US(""),
	//�������
	GLOBAL_FEEDBACK("/member/feedBack.action"),
	//��ȡ��ţ��Դ�ϴ�Token
	QINIU_TOKEN("/member/qnUploadToken.action"),
	/*************************�̻�*****************************/
	MSG_LIST("/sms/list.action"),
	
	/**********************Conpous(�Ż݄�)******************/
	//��ҵ��Ż݄�
	CONPOUS_LIST("/coupons/list.action"),
	//�Լ�ӵ�е��Ż݄�
	CONPOUS_SELFLIST("/coupons/mycoupons.action"),
	//��ȡ�Ż݄�
	CONPOUS_OBTAIN("/coupons/obtatin.action"),
	//ת���Ż݄�
	CONPOUS_TRANS("/coupons/forward.action"),
	//ʹ���Ż݄�
	CONPOUS_USE("/coupons/use.action"),
	
	/*******************����****************************/
	//��������
	LEAVE_MSG_ISSUE("/business/subWM.action"),
	//���Ե��޲���
	LEAVE_MSG_PRAISE("/business/praise.action"),
	//��ȡ�̼�����ǽ�б�
	LEAVE_MSG_OBT("/business/wmList.action"),
	//��ȡ�̼�����ǽ�б�2
	LEAVE_MSG_OBT2("/v2/business/wmList.action"),
	//�鿴ĳ���Ե����б�
	LEAVE_MSG_PRAISELIST("/business/praiseList.action"),
	/**********************Store(�̼�)******************/
	//����wifimac��ȡ�̼���Ϣ
	STORE_WIFIMAC("/business/find.action"),
	//����б�
	STORE_LIST("/business/list.action"),
	//����б�2
	STORE_LIST2("/v2/business/list.action"),
	//ϲ�����
	STORE_INTEREST("/business/love.action"),
	//ȡ��ϲ�����
	STORE_CANCELINTEREST("/business/cancellove.action"),
	//��ϲ�����
	STORE_UNINTEREST("/business/unlove.action"),
	//ȡ����ϲ�����
	STORE_CANCELUNINTEREST("/business/cancelunlove.action"),
	
	
	/**********************Friend(����)******************/
	//�����б�
	FRIEND_LIST("/friends/list.action"),
	//��ڨ
	FRIEND_ACCOST("/sms/accost.action"),
	//�����û�
	FRIEND_BRUSH("/friends/brush.action"),
	//�������
	FRIEND_REBRUSH("/friends/rebrush.action"),
	//��ע����
	FRIEND_FOLLOW("/friends/pay.action"),
	//ȡ����ע
	FRIEND_UNFOLLOW("/friends/repay.action"),
	/**********************Member(��Ա)******************/
	
	//�����ֻ�������֤
	USER_SENDPHONE("/member/sendCode.action"),
	//��֤�ֻ�����ע��
	USER_CHECKPHONE("/member/valPhone.action"),
	//��������¼�ӿ�
	USER_THIRDLOGIN("/member/login.action"),
	//��ͨ��¼�ӿ�
	USER_LOGIN("/member/login.action"),
	//�˳���¼
	USER_LOGINOUT("/member/loginout.action"),
	//����WIFI�ֳ�
	USER_CHANGEWIFI("/member/changeWifi.action"),
    //���¸���״̬
    UPDATE_STATES("/v2/member/updateStates.action"),
	//�޸��û���Ϣ�ӿ�
	USER_IFOUPDATE("/member/modify.action"),
	//�ϴ��û�ͷ��
	USER_HEADUPDATE("/member/uploadHead.action"),
	//�ϴ���Ƭǽ
	USER_PHOTOUPLOAD("/member/uploadPhotos.action"),
	//����״̬�ӿ�
	USER_STATECHANGE("/member/changeState.action"),
	//����Ȩ��
	USER_AUTHORITY("/member/competence.action"),
	//�鿴�ֳ��û��б�
	USER_LOCATELIST("/member/list.action"),
	//�鿴�ֳ��û��б�2
	USER_LOCATELIST2("/v2/member/scene.action"),
	//���ͨѶ¼ʹ�����
	USER_PHONEREGI("/member/valPhone.action");
	
	private final static String PORT="http://mmapiss.meimeime.com:8081/MM";
	private String addr;
	private E_Http_Port(String addr) {
		// TODO Auto-generated constructor stub
		this.addr=addr;
	}
	public String value(){
		return PORT+addr;
	}
}
