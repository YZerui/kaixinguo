package com.hi.module.base.application;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

//import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVMessage;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.Session;
import com.avos.avoscloud.SessionManager;
import com.avos.avoscloud.SignUpCallback;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.hi.common.COMMON;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.face.FaceConversionUtil;
import com.hi.utils.DeviceUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

/**
 * Created with IntelliJ IDEA. User: tangxiaomin Date: 4/19/13 Time: 12:57 PM
 */
public class AppContextApplication extends Application {
	private static AppContextApplication instance;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressWarnings("unused")
	@Override
	public void onCreate() {
		if (Config.DEVELOPER_MODE
				&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectAll().penaltyDeath().build());
		}
		super.onCreate();
		instance = this;
		initImageLoader(getApplicationContext());
		// 初始化应用信息
		AVOSCloud.initialize(this,
				"04g1equ6c5g41aeqhlfq19jyab9u8r6udljkd6ovzrfopjin",
				"snw80ruufnfx9p4yal03xd34qhwo6512yuqfg2oti6qexe6f");
		AVInstallation.getCurrentInstallation().saveInBackground();
		// 启用崩溃错误统计
		// AVAnalytics.enableCrashReport(this.getApplicationContext(), true);

		// 启动即时通讯的注册登录服务
		initIMService();

		// 启用崩溃日志记录
//		 CrashHandler crashHandler = CrashHandler.getInstance();
//		 crashHandler.init(this);
		// 表情数据装载
		initFaceData();
		//创建本地sd卡文件夹
		createSDFolder();
	}

	private void createSDFolder() {
		// TODO Auto-generated method stub
		File file=new File(COMMON.APP_FILE_FOLDER);
		if(!file.exists()){
			file.mkdirs();
			P.v("创建SD文件夹");
		}
	}

	private void initFaceData() {
		// TODO Auto-generated method stub
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				// TODO Auto-generated method stub
				FaceConversionUtil.getInstace().getFileText(
						getApplicationContext());
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub

			}
		}, true);

	}

	public static void initIMService() {
		// TODO Auto-generated method stub
		try {
			final String uid = Dao_SelfIfo.getInstance().getMid();
			if (!DataValidate.checkDataValid(uid)) {
				return;
			}
			if (SessionManager.getInstance(uid).isOpen()) {
				return;
			}
			AVQuery<AVUser> q = AVUser.getQuery();
			q.whereEqualTo("username", uid);
			q.getFirstInBackground(new GetCallback<AVUser>() {
				@Override
				public void done(AVUser object, AVException e) {
					if (e != null) {
					} else {
						// 如果未注册，则对该用户进行注册服务
						if (object == null) {
							final AVUser user = new AVUser();
							user.setUsername(uid);
							user.setPassword(uid);
							user.signUpInBackground(new SignUpCallback() {
								@Override
								public void done(AVException e) {
									if (e != null) {
										// 注册失败
									} else {
										loginSucceed();
									}
								}
							});
						}
						// 如果已经存在该用户，则开启登录服务
						else {
							AVUser.logInInBackground(uid, uid,
									new LogInCallback<AVUser>() {
										@Override
										public void done(AVUser user,
												AVException e) {
											if (e != null) {
												// 登陆失败
											} else {
												loginSucceed();
											}
										}

									});
						}
					}
				}

				private void loginSucceed() {
					// TODO Auto-generated method stub
					// AVUser user = AVUser.getCurrentUser();
					// String selfId = Dao_SelfIfo.getInstance().getMid();
					List<String> peerIds = new LinkedList<String>();
					Session session = SessionManager.getInstance(uid);
					// peerIds.add("8a211dcc49f58e60014a0a279fd30003");
					// peerIds.add("8a211dcc4a9e004e014ab8faba730005");
					// session.setSignatureFactory(new
					// KeepAliveSignatureFactory(
					// AVOSCloud.applicationId, selfId));
					session.open(peerIds);
					// List<String> peer=session.getAllPeers();

					// //发送一条消息
					// AVMessage aMsg=new AVMessage();
					// aMsg.setMessage("测试IM消息");
					// aMsg.setToPeerIds(peerIds);
					// aMsg.setFromPeerId(uid);
					//
					// session.sendMessage(aMsg);
					// P.v("发送IM消息");
				}
			});
		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	public static AppContextApplication getInstance() {
		return instance;
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
//					.memoryCache(new WeakMemoryCache())  
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);
	}

	// 配置
	public static class Config {
		public static final boolean DEVELOPER_MODE = false;
	}

	// 额外类
	public static class Extra {
		public static final String IMAGES = "com.nostra13.example.universalimageloader.IMAGES";
		public static final String IMAGE_POSITION = "com.nostra13.example.universalimageloader.IMAGE_POSITION";
	}

}
