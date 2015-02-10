package com.hi.common;

/**
 * @author MM_Zerui
 * 和服务端进行交互的接口
 */
public class API {

	public final static String WEB_SITE="http://mmapiss.meimeime.com:8081/MM";
	
	/*************************************通用方法********************************/
	/**提交Bug的接口**/
	public final static String BUG_UPLOAD=WEB_SITE+"/uploadBugAction.action";
	
	/**获取优惠劵详情接口**/
	public final static String COMMON_PRIVILEGE_DETAIL=WEB_SITE+"/coupons/find.action";
	
	/**注册接口**/
	public final static String API_REGISTER=WEB_SITE+"/member/login.action";
	/**获得现场用户接口**/
	public final static String LOCAL_REQUEST=WEB_SITE+"/member/list.action";
	/**发送小纸条的接口**/
	public final static String LOCAL_SENDMSG=WEB_SITE+"/sms/accost.action";
	/**修改用户信息的接口**/
	public final static String IFO_ALTER=WEB_SITE+"/member/modify.action";
	/**修改现场状态的接口**/
	public final static String LOCAL_STATE_SWITCH=WEB_SITE+"/member/changeState.action";
	/**修改现场权限的接口**/
	public final static String LOCAL_AUTHOR_SWITCH=WEB_SITE+"/member/competence.action";
	/**获取用户详细信息接口**/
	public final static String USER_DETAIL_IFO=WEB_SITE+"/member/find.action";
	/**关注某用户的接口**/
	public final static String FOLLOW_USER=WEB_SITE+"/friends/pay.action";
	/**取消关注某用户的接口**/
	public final static String CANCEL_FOLLOW_USER=WEB_SITE+"/friends/repay.action";
	/**流放某用户的接口**/
	public final static String FORBIT_USER=WEB_SITE+"/friends/brush.action";
	/**获取朋友列表的接口**/
	public final static String FOLLOWER_LIST=WEB_SITE+"/friends/list.action";
	
	
	
	/***********************************商家***********************************/
	/**获取商家列表**/
	public final static String STORE_LIST=WEB_SITE+"/business/list.action";
	/**查看店家详情**/
	public final static String STORE_DETAIL=WEB_SITE+"/business/find.action";
	/**喜欢店家**/
	public final static String STORE_LOVE=WEB_SITE+"/business/love.action";
	/**取消喜欢店家**/
	public final static String STORE_LOVE_CANCEL=WEB_SITE+"/business/cancellove.action";
	/**不喜欢店家**/
	public final static String STORE_DISLIKE=WEB_SITE+"/business/unlove.action";
	/**取消不喜欢店家**/
	public final static String STORE_DISLIKE_CANCEL=WEB_SITE+"/business/cancelunlove.action";
	/************************************优惠劵*******************************/
	/**查看自己拥有的优惠劵**/
	public final static String PRIVILEGE_SELF_OBTAIN=WEB_SITE+"/coupons/mycoupons.action";
	/**获取优惠劵**/
	public final static String PRIVILEGE_OBTAIN=WEB_SITE+"/coupons/obtatin.action"; 
	/**转发优惠劵**/
	public final static String PRIVILEGE_SHARE=WEB_SITE+"/coupons/forward.action";
	/**使用优惠劵**/
	public final static String PRIVILEGE_USE=WEB_SITE+"/coupons/use.action";

	
	
	/**获取活动内容**/
	public final static String ACTIVITY_LIST=WEB_SITE+"/activity/list.action";
	/**上传头像**/
	public final static String SELF_HEAD_UPLOAD=WEB_SITE+"/member/uploadHead.action";
	/**上传照片墙**/
	public final static String SELF_WALL_UPLOAD=WEB_SITE+"/member/uploadPhotos.action";
	
	/**获得某店所有优惠劵**/
	public final static String PRIVILEGE_TOTAL=WEB_SITE+"/coupons/list.action";
	
	
	/**提交手机号码**/
	public final static String PHONE_SUBMIT=WEB_SITE+"/member/sendCode.action";
//	/**验证手机号码**/
//	public final static String PHONE_AUTHO=WEB_SITE+"";
	
	/**获取消息缓存信息**/
	public final static String MSG_CACHE_TOTAL=WEB_SITE+"/sms/list.action";
	
	/**退出登录的操作**/
	public final static String QUIT_LOGIN=WEB_SITE+"/member/loginout.action";
	
	/**发送手机验证码的操作**/
//	public final static String PHONE_AUTHOR=WEB_SITE+"/member/valPhone.action";
	
	/**手机注册**/
	public final static String PHONE_REGISTER=WEB_SITE+"/member/valPhone.action";
	
	/**手机登录**/
	public final static String PHONE_LOGIN=WEB_SITE+"/member/login.action";
	
	/**检测通信录的使用情况**/
	public final static String CONTACT_PHONE_CHECK=WEB_SITE+"/member/checkPhone.action";
	
	/**现场wifi签到的接口**/
	public final static String LOCAL_WIFI_SIGN=WEB_SITE+"/member/changeWifi.action";
}
