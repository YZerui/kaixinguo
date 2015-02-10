package com.hi.common;

/**
 * @author MM_Zerui һЩ�̶�����
 */
public class PARAMS {
	/** ��������ʱ���� **/
	public final static int HTTP_REQUEST_TIMEOUT = 5000;
	/** �����������ݶ�ȡ��ʱ���� **/
	public final static int HTTP_READ_TIMEOUT = 5000;

	public final static int COLOR_TRANSPARENT = 0x66000000;

	/** ҳ����ʼ������ʱ�� **/
	public final static int APP_START_PAGE_LENGTH = 1000;
	/** Activity������ **/
	public static int REQUEST_CODE;

	/** ���յķ����� **/
	public final static int PHOTO_CAPTURE = 1001;
	/** ��ͼ�ķ����� **/
	public final static int PHOTO_CROP = 1002;
	/** ��Ƭ��С���� **/
	public static final int SCALE = 5;

	/** ��Ƭ�غ�����״̬ **/
	public final static int PHOTO_CAPTURE_COMPLETE = 1003;
	/** ��ͼ�ɹ����״̬ **/
	public final static int PHOTO_CAPTURE_SUCCESS = 1004;
	/******************************************AVOS���ò���*************************************/
	
	
	
	/******************************************Bug�ύ����*************************************/
	public final static String systemid="android";
	public final static String psw="34kj3365h";
	/*************************************** ������Ϣ�޸Ĳ��� **********************************/
	public final static int ALTER_NAME = 1;
	public final static int ALTER_GENDER = 2;
	public final static int ALTER_INTEREST = 4;
	public final static int ALTER_BIRTHDAY = 3;
	public final static int ALTER_LABEL = 5;
	public final static int ALTER_WORK = 6;
	public final static int ALTER_CURRENT_WIFI_STATE = 7;
	public final static int ALTER_NORMAL_STATE = 8;
	// ��Ů��Ϣ�ı�ʶ
	public final static String MAN = "1";
	public final static String WOMEN = "0";

	// ��ƬǽͼƬ����
	public final static int IMG_WALL_SIZE = 300;

	/************************************** ��¼ *******************************************/

	public final static String THIRD_LOGIN_WEIBO = "2";
	public final static String THIRD_LOGIN_QQ = "1";

	public final static int LOGIN_TYPE_THIRDPLAT = 101;// ��������¼
	public final static int LOGIN_TYPE_PHONE = 102;// �ֻ���¼
	public final static int LOGIN_TYPE_NULL = 100;// �޵�¼

	public final static String DEVICE_SYSTEM = "android";

	// �ֳ�״̬
	public final static int CURRENT_STATE = 2;
	// ͨ��״̬
	public final static int NORMAL_STATE = 1;

	/** ͼ�񻺳�������趨 **/
	public final static int LIST_IMG = 180;
	public final static int DETAIL_IMG = 800;

	/** ����ʱģʽ���趨 **/
	public final static int TIMER_SECOND = 200;
	public final static int TIMER_MINUTE = 201;
	public final static int TIMER_HOUR = 202;
	public final static int TIMER_HOUR_MINUTE = 203;
	public final static int TIMER_MINUTE_SECOND = 204;
	public final static int TIMER_ALL = 205;

	/** http�������Ӧ�� **/
	public final static int HTTP_QUIT_LOGIN = 0x001;

	/** �豸ϵͳ���� **/
	public final static String LOGIN_SYSTEM_TYPE = "android";

	/** �û���ϵ��ʶ **/
	public final static int FOLLOW_NONE = 0;
	public final static int FOLLOW_ME = 1;
	public final static int FOLLOW_OTHER = 2;
	public final static int FOLLOW_EACH_OTHER = 3;

	/** ��Ϣ���͵����� **/
	public final static String MSG_FOLLOW = "follow";
	public final static String MSG_ACCOST = "accost";
	public final static String MSG_SYSTEM = "system";
	public final static String MSG_COUPON = "coupon";

	/** ͨѶ¼�ĺ������� **/
	public final static String CONTACT_UNREGISTER = "0";// δע��
	public final static String CONTACT_REGISTER = "1";// �Ѿ�ע��
	// public final static int CONTACT_FOLLOW = "2";// �Ѿ���ע
	// ������ϵ�˼�¼��ÿ����ȡ������
	public final static int CONTACT_INDEX_LENGTH = 30;

	/** �̶����� **/
	public final static long MINUTE_MILLISECONDS = 60000; // һ���ӵĺ�����
	public final static long HOUR_MILLISECONDS = 3600000;// һСʱ�ĺ�����

	/** �㲥�����ź� **/
	final public static String MSG_RECEIVE = "MSG_RECEIVE";
	final public static String MSG_LIST_RECEIVE = "MSG_LIST_RECEIVE";
	final public static String MSG_CHAT_RECEIVE = "MSG_CHAT_RECEIVE";
	final public static String CONTACTS_DONE_RECEIVE = "CONTACTS_DONE_RECEIVE";

	// ������Ϣ�Ի�֪ͨ�ı�ʶ
	final public static String MSG_CHAT_DATA_EXTRA = "MSG_CHAT_DATA_EXTRA";

	/** ��¼ˢ��ʱ�� **/
	// final public static String MSG_REFRESH_TIME="MSG_REFRESH_TIME";

	/************************** ���ݿ� **********************/
	// ��ݱ�ʶ
	public final static int IDENTITY_SELF = 0;
	public final static int IDENTITY_OPPOSITE = 1;
	// ��Ϣ���ͱ�ʶ
	public final static int MSG_TYPE_ACCOST = 0;
	public final static int MSG_TYPE_RECOMMENT = 1;
	public final static int MSG_TYPE_FOLLOW = 2;
	public final static int MSG_TYPE_INVITE = 3;
	// ���ݶ�������
	public final static int MSG_TARGET_USER = 0;
	public final static int MSG_TARGET_STORE = 1;
	// ���ݿ���ÿ�β�ѯ������
	public final static int DAO_INDEX_LENGTH = 4;
	public final static int SEQ_DAO_INDEX_LENGTH = 10;

	/************************* �̼�ģ�� ************************/
	public final static String STORE_LOVE = "1";
	public final static String STORE_DEFAULT = "0";
	public final static String STORE_DISLIKE = "-1";
	public final static String PRIVILEGE_FRESH_NUM="10";
	
	/********************** ShareSDK ***************************/
	public final static String APP_KEY = "2f2f9f14ee00";
	public final static String APP_SECRET = "d9b6fbc25d57efdd3bee705cc8d2973d";
}
