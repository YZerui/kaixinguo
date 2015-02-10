package com.hi.module.base.application;

import java.util.HashMap;

import org.json.JSONObject;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

import com.android.ruifeng.hi.R;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.DeviceUtils;
import com.hi.utils.FunUtils;
import com.hi.utils.ScreenShot;
import com.hi.view.customWidget.WheelView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @author MM_Zerui ��ʾ����Ӧ�õĽ���
 */

public class ShareUnitDialog extends Activity implements PlatformActionListener
		 {

	private static int screenHeight;
	private Context context;
	private String share_local_imgurl;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_unit_dialog);
		ViewUtils.inject(this);
		initWindow();
		init();
			

	}
	private void init() {
		// TODO Auto-generated method stub
		
		context = this;
		ShareSDK.initSDK(context);
		share_local_imgurl = DBUtils.getSharedPreStr(context, "SHAREIMG",
				"NULL");
	}
	@OnClick(R.id.share_cancel_btn)
	public void cancelShare(View v){
		finish();
		AnimationUtil.finishOut2Bottom(context);
	}
	@OnClick(R.id.WeiboShare)
	public void weiBoShare(View v){
		ShareParams sp=new ShareParams();
		sp.setText("΢������");
		sp.setImagePath(share_local_imgurl);
		Platform weibo = ShareSDK.getPlatform(context, SinaWeibo.NAME);
		weibo.setPlatformActionListener(this); // ���÷����¼��ص�
		// ִ��ͼ�ķ���
		weibo.share(sp);
	}
	@OnClick(R.id.WeiXinShare)
	public void weiXinShare(View v){
		ShareParams sp=new ShareParams();
		sp.setShareType(Platform.SHARE_IMAGE);
		sp.setText("΢�ŷ���");
		sp.setImagePath(share_local_imgurl);
		Platform weChat = ShareSDK.getPlatform(context, Wechat.NAME);
		weChat.setPlatformActionListener(this); // ���÷����¼��ص�
		// ִ��ͼ�ķ���
		weChat.share(sp);
	}
	@OnClick(R.id.WeiXinFriShare)
	public void weiXinFriShare(View v){
		ShareParams sp=new ShareParams();
		sp.setShareType(Platform.SHARE_IMAGE);
		sp.setText("΢������Ȧ����");
		sp.setImagePath(share_local_imgurl);
		Platform weChat = ShareSDK.getPlatform(context, WechatMoments.NAME);
		weChat.setPlatformActionListener(this); // ���÷����¼��ص�
		// ִ��ͼ�ķ���
		weChat.share(sp);
	}


	private void initWindow() {
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();// ��ȡ��Ļ�߶�
		WindowManager.LayoutParams lp = getWindow().getAttributes();// //lp�����˲��ֵĺܶ���Ϣ��ͨ��lp�����öԻ���Ĳ���
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
		lp.height = screenHeight / 5*2;// lp�߶�����Ϊ��Ļ��һ��
//		lp.height = DeviceUtils.dip2px(context, 40)+
//				WheelView.ADDITIONAL_ITEM_HEIGHT*WheelView.DEF_VISIBLE_ITEMS;
		
		getWindow().setAttributes(lp);// �����ú����Ե�lpӦ�õ��Ի���
	}
	/**
	 * ��Ļ��ͼ�ķ���
	 */
	private String ScreenShotMethod() {
		// TODO Auto-generated method stub
		return ScreenShot.shoot(ShareUnitDialog.this);
	}
	@Override
	public void onCancel(Platform arg0, int arg1) {
		// TODO Auto-generated method stub
		System.out.println("ȡ������");
	}
	@Override
	public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
		// TODO Auto-generated method stub
		System.out.println("����ɹ�");
	}
	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {
		// TODO Auto-generated method stub
		System.out.println("�������");
		Looper.prepare();
		com.hi.utils.ViewHandleUtils.toastNormal("����ʧ��,�㻹δ��װ��Ӧ���أ�");
		Looper.loop();
		
	}


}
