package com.hi.common;

/**
 * @author MM_Zerui
 *	异常代码，用于标识不同的异常状态
 */
public class EXCEPTION_CODE {


	/**数据请求失败**/
	public static final int REQUEST_DATAERROR=1112;
	/**返回数据为空**/
	public static final int REQUEST_DATANULL=1114;
	/**请求成功**/
	public static final int REQUEST_SUCCESS=1113;
	
	
	/**号码获取成功**/
	public static final int CONTACT_OBT_SUCCESS=1000;
	/**号码获取失败**/
	public static final int CONTACT_OBT_FAIL=1001;
	
	/**消息缓存刷新完毕**/
	public static final int LIST_REFRESH_DONE=400;
	/**消息缓存刷新完毕**/
	public static final int LIST_REFRESH_DONE_NOTDATA=401;
}
