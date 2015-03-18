package com.hi.common.http;

/**
 * 接口
 * @author MM_Zerui
 *
 */
public enum E_Http_Port {
	/**通用**/
	COM_MEMBER("/member/find.action"),
	COM_BUSINESS("/business/find.action"),
	COM_ACTIVITY("/activity/find.action"),
	/*********************全局**************************/
	//关于我们
	ABOUT_US(""),
	//反馈意见
	GLOBAL_FEEDBACK("/member/feedBack.action"),
	//获取七牛资源上传Token
	QINIU_TOKEN("/member/qnUploadToken.action"),
	/*************************商机*****************************/
	MSG_LIST("/sms/list.action"),
	
	/**********************Conpous(优惠劵)******************/
	//店家的优惠劵
	CONPOUS_LIST("/coupons/list.action"),
	//自己拥有的优惠劵
	CONPOUS_SELFLIST("/coupons/mycoupons.action"),
	//获取优惠劵
	CONPOUS_OBTAIN("/coupons/obtatin.action"),
	//转发优惠劵
	CONPOUS_TRANS("/coupons/forward.action"),
	//使用优惠劵
	CONPOUS_USE("/coupons/use.action"),
	
	/*******************留言****************************/
	//发布留言
	LEAVE_MSG_ISSUE("/business/subWM.action"),
	//留言点赞操作
	LEAVE_MSG_PRAISE("/business/praise.action"),
	//获取商家留言墙列表
	LEAVE_MSG_OBT("/business/wmList.action"),
	//获取商家留言墙列表2
	LEAVE_MSG_OBT2("/v2/business/wmList.action"),
	//查看某留言点赞列表
	LEAVE_MSG_PRAISELIST("/business/praiseList.action"),
	/**********************Store(商家)******************/
	//根据wifimac获取商家信息
	STORE_WIFIMAC("/business/find.action"),
	//店家列表
	STORE_LIST("/business/list.action"),
	//店家列表2
	STORE_LIST2("/v2/business/list.action"),
	//喜欢店家
	STORE_INTEREST("/business/love.action"),
	//取消喜欢店家
	STORE_CANCELINTEREST("/business/cancellove.action"),
	//不喜欢店家
	STORE_UNINTEREST("/business/unlove.action"),
	//取消不喜欢店家
	STORE_CANCELUNINTEREST("/business/cancelunlove.action"),
	
	
	/**********************Friend(朋友)******************/
	//朋友列表
	FRIEND_LIST("/friends/list.action"),
	//搭讪
	FRIEND_ACCOST("/sms/accost.action"),
	//屏蔽用户
	FRIEND_BRUSH("/friends/brush.action"),
	//解除屏蔽
	FRIEND_REBRUSH("/friends/rebrush.action"),
	//关注好友
	FRIEND_FOLLOW("/friends/pay.action"),
	//取消关注
	FRIEND_UNFOLLOW("/friends/repay.action"),
	/**********************Member(人员)******************/
	
	//发送手机号码验证
	USER_SENDPHONE("/member/sendCode.action"),
	//验证手机短信注册
	USER_CHECKPHONE("/member/valPhone.action"),
	//第三方登录接口
	USER_THIRDLOGIN("/member/login.action"),
	//普通登录接口
	USER_LOGIN("/member/login.action"),
	//退出登录
	USER_LOGINOUT("/member/loginout.action"),
	//更换WIFI现场
	USER_CHANGEWIFI("/member/changeWifi.action"),
    //更新个人状态
    UPDATE_STATES("/v2/member/updateStates.action"),
	//修改用户信息接口
	USER_IFOUPDATE("/member/modify.action"),
	//上传用户头像
	USER_HEADUPDATE("/member/uploadHead.action"),
	//上传照片墙
	USER_PHOTOUPLOAD("/member/uploadPhotos.action"),
	//更改状态接口
	USER_STATECHANGE("/member/changeState.action"),
	//设置权限
	USER_AUTHORITY("/member/competence.action"),
	//查看现场用户列表
	USER_LOCATELIST("/member/list.action"),
	//查看现场用户列表2
	USER_LOCATELIST2("/v2/member/scene.action"),
	//检测通讯录使用情况
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
