package com.hi.common;

/**
 * @author MM_Zerui
 * �ͷ���˽��н����Ľӿ�
 */
public class API {

	public final static String WEB_SITE="http://mmapiss.meimeime.com:8081/MM";
	
	/*************************************ͨ�÷���********************************/
	/**�ύBug�Ľӿ�**/
	public final static String BUG_UPLOAD=WEB_SITE+"/uploadBugAction.action";
	
	/**��ȡ�Ż݄�����ӿ�**/
	public final static String COMMON_PRIVILEGE_DETAIL=WEB_SITE+"/coupons/find.action";
	
	/**ע��ӿ�**/
	public final static String API_REGISTER=WEB_SITE+"/member/login.action";
	/**����ֳ��û��ӿ�**/
	public final static String LOCAL_REQUEST=WEB_SITE+"/member/list.action";
	/**����Сֽ���Ľӿ�**/
	public final static String LOCAL_SENDMSG=WEB_SITE+"/sms/accost.action";
	/**�޸��û���Ϣ�Ľӿ�**/
	public final static String IFO_ALTER=WEB_SITE+"/member/modify.action";
	/**�޸��ֳ�״̬�Ľӿ�**/
	public final static String LOCAL_STATE_SWITCH=WEB_SITE+"/member/changeState.action";
	/**�޸��ֳ�Ȩ�޵Ľӿ�**/
	public final static String LOCAL_AUTHOR_SWITCH=WEB_SITE+"/member/competence.action";
	/**��ȡ�û���ϸ��Ϣ�ӿ�**/
	public final static String USER_DETAIL_IFO=WEB_SITE+"/member/find.action";
	/**��עĳ�û��Ľӿ�**/
	public final static String FOLLOW_USER=WEB_SITE+"/friends/pay.action";
	/**ȡ����עĳ�û��Ľӿ�**/
	public final static String CANCEL_FOLLOW_USER=WEB_SITE+"/friends/repay.action";
	/**����ĳ�û��Ľӿ�**/
	public final static String FORBIT_USER=WEB_SITE+"/friends/brush.action";
	/**��ȡ�����б�Ľӿ�**/
	public final static String FOLLOWER_LIST=WEB_SITE+"/friends/list.action";
	
	
	
	/***********************************�̼�***********************************/
	/**��ȡ�̼��б�**/
	public final static String STORE_LIST=WEB_SITE+"/business/list.action";
	/**�鿴�������**/
	public final static String STORE_DETAIL=WEB_SITE+"/business/find.action";
	/**ϲ�����**/
	public final static String STORE_LOVE=WEB_SITE+"/business/love.action";
	/**ȡ��ϲ�����**/
	public final static String STORE_LOVE_CANCEL=WEB_SITE+"/business/cancellove.action";
	/**��ϲ�����**/
	public final static String STORE_DISLIKE=WEB_SITE+"/business/unlove.action";
	/**ȡ����ϲ�����**/
	public final static String STORE_DISLIKE_CANCEL=WEB_SITE+"/business/cancelunlove.action";
	/************************************�Ż݄�*******************************/
	/**�鿴�Լ�ӵ�е��Ż݄�**/
	public final static String PRIVILEGE_SELF_OBTAIN=WEB_SITE+"/coupons/mycoupons.action";
	/**��ȡ�Ż݄�**/
	public final static String PRIVILEGE_OBTAIN=WEB_SITE+"/coupons/obtatin.action"; 
	/**ת���Ż݄�**/
	public final static String PRIVILEGE_SHARE=WEB_SITE+"/coupons/forward.action";
	/**ʹ���Ż݄�**/
	public final static String PRIVILEGE_USE=WEB_SITE+"/coupons/use.action";

	
	
	/**��ȡ�����**/
	public final static String ACTIVITY_LIST=WEB_SITE+"/activity/list.action";
	/**�ϴ�ͷ��**/
	public final static String SELF_HEAD_UPLOAD=WEB_SITE+"/member/uploadHead.action";
	/**�ϴ���Ƭǽ**/
	public final static String SELF_WALL_UPLOAD=WEB_SITE+"/member/uploadPhotos.action";
	
	/**���ĳ�������Ż݄�**/
	public final static String PRIVILEGE_TOTAL=WEB_SITE+"/coupons/list.action";
	
	
	/**�ύ�ֻ�����**/
	public final static String PHONE_SUBMIT=WEB_SITE+"/member/sendCode.action";
//	/**��֤�ֻ�����**/
//	public final static String PHONE_AUTHO=WEB_SITE+"";
	
	/**��ȡ��Ϣ������Ϣ**/
	public final static String MSG_CACHE_TOTAL=WEB_SITE+"/sms/list.action";
	
	/**�˳���¼�Ĳ���**/
	public final static String QUIT_LOGIN=WEB_SITE+"/member/loginout.action";
	
	/**�����ֻ���֤��Ĳ���**/
//	public final static String PHONE_AUTHOR=WEB_SITE+"/member/valPhone.action";
	
	/**�ֻ�ע��**/
	public final static String PHONE_REGISTER=WEB_SITE+"/member/valPhone.action";
	
	/**�ֻ���¼**/
	public final static String PHONE_LOGIN=WEB_SITE+"/member/login.action";
	
	/**���ͨ��¼��ʹ�����**/
	public final static String CONTACT_PHONE_CHECK=WEB_SITE+"/member/checkPhone.action";
	
	/**�ֳ�wifiǩ���Ľӿ�**/
	public final static String LOCAL_WIFI_SIGN=WEB_SITE+"/member/changeWifi.action";
}
