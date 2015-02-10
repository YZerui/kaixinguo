package com.hi.common;

/**
 * @author MM_Zerui 一些固定参数
 */
public class PARAMS {
	/** 网络请求超时限制 **/
	public final static int HTTP_REQUEST_TIMEOUT = 5000;
	/** 网络请求数据读取超时限制 **/
	public final static int HTTP_READ_TIMEOUT = 5000;

	public final static int COLOR_TRANSPARENT = 0x66000000;

	/** 页面起始动画的时间 **/
	public final static int APP_START_PAGE_LENGTH = 1000;
	/** Activity返回码 **/
	public static int REQUEST_CODE;

	/** 拍照的返回码 **/
	public final static int PHOTO_CAPTURE = 1001;
	/** 截图的返回码 **/
	public final static int PHOTO_CROP = 1002;
	/** 照片缩小比例 **/
	public static final int SCALE = 5;

	/** 相片截后的完成状态 **/
	public final static int PHOTO_CAPTURE_COMPLETE = 1003;
	/** 截图成功后的状态 **/
	public final static int PHOTO_CAPTURE_SUCCESS = 1004;
	/******************************************AVOS配置参数*************************************/
	
	
	
	/******************************************Bug提交参数*************************************/
	public final static String systemid="android";
	public final static String psw="34kj3365h";
	/*************************************** 个人信息修改参数 **********************************/
	public final static int ALTER_NAME = 1;
	public final static int ALTER_GENDER = 2;
	public final static int ALTER_INTEREST = 4;
	public final static int ALTER_BIRTHDAY = 3;
	public final static int ALTER_LABEL = 5;
	public final static int ALTER_WORK = 6;
	public final static int ALTER_CURRENT_WIFI_STATE = 7;
	public final static int ALTER_NORMAL_STATE = 8;
	// 男女信息的标识
	public final static String MAN = "1";
	public final static String WOMEN = "0";

	// 照片墙图片质量
	public final static int IMG_WALL_SIZE = 300;

	/************************************** 登录 *******************************************/

	public final static String THIRD_LOGIN_WEIBO = "2";
	public final static String THIRD_LOGIN_QQ = "1";

	public final static int LOGIN_TYPE_THIRDPLAT = 101;// 第三方登录
	public final static int LOGIN_TYPE_PHONE = 102;// 手机登录
	public final static int LOGIN_TYPE_NULL = 100;// 无登录

	public final static String DEVICE_SYSTEM = "android";

	// 现场状态
	public final static int CURRENT_STATE = 2;
	// 通用状态
	public final static int NORMAL_STATE = 1;

	/** 图像缓冲参数的设定 **/
	public final static int LIST_IMG = 180;
	public final static int DETAIL_IMG = 800;

	/** 倒计时模式的设定 **/
	public final static int TIMER_SECOND = 200;
	public final static int TIMER_MINUTE = 201;
	public final static int TIMER_HOUR = 202;
	public final static int TIMER_HOUR_MINUTE = 203;
	public final static int TIMER_MINUTE_SECOND = 204;
	public final static int TIMER_ALL = 205;

	/** http请求的响应码 **/
	public final static int HTTP_QUIT_LOGIN = 0x001;

	/** 设备系统类型 **/
	public final static String LOGIN_SYSTEM_TYPE = "android";

	/** 用户关系标识 **/
	public final static int FOLLOW_NONE = 0;
	public final static int FOLLOW_ME = 1;
	public final static int FOLLOW_OTHER = 2;
	public final static int FOLLOW_EACH_OTHER = 3;

	/** 消息推送的类型 **/
	public final static String MSG_FOLLOW = "follow";
	public final static String MSG_ACCOST = "accost";
	public final static String MSG_SYSTEM = "system";
	public final static String MSG_COUPON = "coupon";

	/** 通讯录的号码类型 **/
	public final static String CONTACT_UNREGISTER = "0";// 未注册
	public final static String CONTACT_REGISTER = "1";// 已经注册
	// public final static int CONTACT_FOLLOW = "2";// 已经关注
	// 本地联系人记录中每次提取的数量
	public final static int CONTACT_INDEX_LENGTH = 30;

	/** 固定常量 **/
	public final static long MINUTE_MILLISECONDS = 60000; // 一分钟的毫秒数
	public final static long HOUR_MILLISECONDS = 3600000;// 一小时的毫秒数

	/** 广播发送信号 **/
	final public static String MSG_RECEIVE = "MSG_RECEIVE";
	final public static String MSG_LIST_RECEIVE = "MSG_LIST_RECEIVE";
	final public static String MSG_CHAT_RECEIVE = "MSG_CHAT_RECEIVE";
	final public static String CONTACTS_DONE_RECEIVE = "CONTACTS_DONE_RECEIVE";

	// 传递消息对话通知的标识
	final public static String MSG_CHAT_DATA_EXTRA = "MSG_CHAT_DATA_EXTRA";

	/** 记录刷新时间 **/
	// final public static String MSG_REFRESH_TIME="MSG_REFRESH_TIME";

	/************************** 数据库 **********************/
	// 身份标识
	public final static int IDENTITY_SELF = 0;
	public final static int IDENTITY_OPPOSITE = 1;
	// 消息类型标识
	public final static int MSG_TYPE_ACCOST = 0;
	public final static int MSG_TYPE_RECOMMENT = 1;
	public final static int MSG_TYPE_FOLLOW = 2;
	public final static int MSG_TYPE_INVITE = 3;
	// 内容对象类型
	public final static int MSG_TARGET_USER = 0;
	public final static int MSG_TARGET_STORE = 1;
	// 数据库中每次查询的条数
	public final static int DAO_INDEX_LENGTH = 4;
	public final static int SEQ_DAO_INDEX_LENGTH = 10;

	/************************* 商家模块 ************************/
	public final static String STORE_LOVE = "1";
	public final static String STORE_DEFAULT = "0";
	public final static String STORE_DISLIKE = "-1";
	public final static String PRIVILEGE_FRESH_NUM="10";
	
	/********************** ShareSDK ***************************/
	public final static String APP_KEY = "2f2f9f14ee00";
	public final static String APP_SECRET = "d9b6fbc25d57efdd3bee705cc8d2973d";
}
