package com.hi.module.locale.ui.leavenote;


import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.format.utils.Md5Util;
import com.hi.common.EXCEPTION_CODE;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.local.model.Req_issueMsg;
import com.hi.http.local.req.Http_IssueMsg;
import com.hi.module.base.application.AppManager;
import com.hi.module.base.img.ImageLoader;
import com.hi.module.base.img.ImageSelectPage;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DeviceUtils;
import com.hi.utils.PathUtils;
import com.hi.utils.ViewHandleUtils;
import com.hi.utils.imgLoadUtil.ImageManager2;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 发送留言内容的编辑页面
 *
 * @author MM_Zerui
 */
public class LeaveNoteEditActivity extends NormalActivity implements ImageSelectPage.ImageSelectListener {
    public static boolean issueSuccess;
    @ViewInject(R.id.topBar)
    private CustomTopbarView topBar;
    @ViewInject(R.id.fly_msg_content)
    private EditText msgEditText;
    @ViewInject(R.id.fly_msg_count)
    private TextView msgCount;
    @ViewInject(R.id.imgSelect)
    private ImageView imgSelect;

    public static String path;
    public static String sourcePath;
    private boolean isHasImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.local_leavenote_edit_layout);
        ViewUtils.inject(this);
        super.onCreate(savedInstanceState);

    }


    @Override
    public void obtainIntentValue() {
        // TODO Auto-generated method stub
//		Intent intent=getIntent();
//		UID=intent.getStringExtra("DATA0");
//		head=intent.getStringExtra("DATA1");
//		nickName=intent.getStringExtra("DATA2");
    }

    @Override
    public void initResource() {
        // TODO Auto-generated method stub
        issueSuccess = false;
        path = "";
        sourcePath = "";
        msgEditText.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                temp = s;
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {
                int number = s.length();
                msgCount.setText(String.valueOf(number));
                selectionStart = msgEditText.getSelectionStart();
                selectionEnd = msgCount.getSelectionEnd();
                if (temp.length() > 140) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    msgEditText.setText(s);
                    msgEditText.setSelection(tempSelection);
                }
            }
        });
    }

    @OnClick(R.id.imgSelect)
    private void selectClick(View v) {
        AnimationUtil.in2TopIntent(context, ImgSelectDialog.class);
    }

    @Override
    public void onClickListener() {
        // TODO Auto-generated method stub
        topBar.setCallBack(new topBarCallBack() {
            @Override
            public void call_backBtnListener() {
                // TODO Auto-generated method stub
                super.call_backBtnListener();
                outFinish();
            }

            @Override
            public void call_rightTextBtnListener() {
                // TODO Auto-generated method stub
                super.call_rightTextBtnListener();
                //发送留言操作
                leaveMsgIssue();
            }

            private void leaveMsgIssue() {
                // TODO Auto-generated method stub
                String content = msgEditText.getText().toString().trim();
                if (!DataValidate.checkDataValid(content)) {
                    toast.setText("请先输入你的留言内容哦");
                    return;
                }
                Req_issueMsg reqBean = new Req_issueMsg();
                String imgPath = "";
                //待修改
                if (isHasImage) {

                    if (DataValidate.checkDataValid(path)) {
                        imgPath = path;
                    }
                    if (DataValidate.checkDataValid(sourcePath)) {

                        imgPath = sourcePath;
                    }
                }
                if (DataValidate.checkDataValid(imgPath)) {
                    final String newPath = PathUtils.getChatFilePath(Md5Util.myUUID());
                    ViewHandleUtils.compressImage(imgPath, newPath);
                    reqBean.setImg(newPath);
                }

                reqBean.setContent(content);
                reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
                reqBean.setWifiMac(DeviceUtils.getWifiMac());//测试数据

                new Http_IssueMsg(new Call_httpData<Class<?>>() {

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub
                        topBar.setTitle("发布...").setProVisibility(true);
                    }

                    @Override
                    public void onSuccess(Class<?> datas) {
                        // TODO Auto-generated method stub
                        issueSuccess = true;
                        finish();
                    }

                    @Override
                    public void onFail() {
                        // TODO Auto-generated method stub
                        toast.setText("发布失败,网络错误或不在wifi状态哦");
                    }

                    @Override
                    public void onFinally() {
                        // TODO Auto-generated method stub
                        topBar.setProVisibility(false).setRighTextVisibility(true).setTitle("传递你的足迹");
                    }

                }).onParams(reqBean).onAction();
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //选取前清除缓存
//		ImageLoader.getInstance().recycleCache();
        ImageSelectPage.addImageSelect(this);
        //待修改
//		if(ImgSelectDialog.ifSelect){
////			ImageDisplayer.getInstance(context).
////				displayBmp(imgSelect,path,sourcePath);
//			if(DataValidate.checkDataValid(path)){
//				ImageLoader.getInstance().loadImage(path, imgSelect);
//			}else {
//				ImageLoader.getInstance().loadImage(sourcePath, imgSelect);
//			}
//
//		}
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        outFinish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageSelectPage.removeSelectListener(this);
    }

    @Override
    public void outFinish() {
        // TODO Auto-generated method stub
        finish();
        AnimationUtil.finishOut2Bottom(context);
    }

    @Override
    public void isImageSelect(boolean isSelect) {
        isHasImage = isSelect;
        if (!isSelect) {
            return;
        }
        if (DataValidate.checkDataValid(path)) {
//            ImageLoader.getInstance().loadImage(path, imgSelect);
            ImageManager2.from(context).displayImage(imgSelect, path, R.drawable.default_load_img, 100, 100);
        } else {
//            ImageLoader.getInstance().loadImage(sourcePath, imgSelect);
            ImageManager2.from(context).displayImage(imgSelect, sourcePath, R.drawable.default_load_img, 100, 100);
        }
    }
}
