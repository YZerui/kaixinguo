package com.hi.module.self.ui;

import java.util.ArrayList;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomItemView;
import com.customview.view.CustomTopbarView;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_Gender;
import com.hi.common.writing.W_UserInfo;
import com.hi.dao.model.T_SelfIfo;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.superClass.RequestActivity;
import com.hi.module.locale.model.RecvUserIfoBean;
import com.hi.module.self.model.RecvActivityBean;
import com.hi.utils.AnimationUtil;
import com.hi.view.PullScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 个人主页
 *
 * @author MM_Zerui
 */
public class MyselfActivity extends RequestActivity {
    @ViewInject(R.id.topBar)
    private CustomTopbarView topBar;
    @ViewInject(R.id.self_Icon)
    private ImageView mHeadImg;
    @ViewInject(R.id.selfGenderIcon)
    private ImageView selfGenderImg;
    @ViewInject(R.id.self_Name)
    private TextView selfName;
    @ViewInject(R.id.selfAge)
    private TextView selfAge;
    @ViewInject(R.id.xingZuo)
    private TextView selfXingZuo;
    @ViewInject(R.id.action_to_img_set)
    private ImageView toImgSetBtn;
    @ViewInject(R.id.activityItem)
    private CustomItemView activityItem;
    @ViewInject(R.id.stateItem)
    private CustomItemView stateItem;
    @ViewInject(R.id.labelItem)
    private CustomItemView labelItem;
    @ViewInject(R.id.self_Work)
    private TextView selfWork;
    @ViewInject(R.id.scroll_view)
    private PullScrollView mScrollView;

    private RecvUserIfoBean selfInfoBean;
    private RelativeLayout toActivityBtn;
    ArrayList<RecvActivityBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setContentView(R.layout.self_page_layout);
        ViewUtils.inject(this);
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initResource() {
        // TODO Auto-generated method stub
        mScrollView.setHeader(mHeadImg);

        loadOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.default_img_head)
                .showImageOnFail(R.drawable.default_img_head)
//		.showStubImage(R.drawable.default_img_head)
                .cacheInMemory(true)
                .cacheOnDisc(true).build();


    }

    @OnClick(R.id.action_to_img_set)
    public void imgSetClick(View v) {
        AnimationUtil.tab_in2TopIntent(context,
                ImgWallSettingActivity.class);
    }

    @Override
    protected void setOnClickListener() {
        topBar.setCallBack(new topBarCallBack() {
            @Override
            public void call_rightTextBtnListener() {
                // TODO Auto-generated method stub
                super.call_rightTextBtnListener();
                AnimationUtil.tab_in2LeftIntent(context, IfoSetListPage.class);
            }
        });
    }

    @OnClick(R.id.activityItem)
    public void toActivityClick(View v) {
        toast.setText("活动版块即将开放啦");
    }

    @Override
    protected void obtainIntentValue() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initIfo();

    }

    public void initIfo() {
        T_SelfIfo infoBean = Dao_SelfIfo.getInstance();
        W_UserInfo.Default(E_DB_SelfIfo.nickName, selfName, infoBean.getNickName());
        W_UserInfo.Default(E_DB_SelfIfo.occupation, selfWork, infoBean.getOccupation());
        W_UserInfo.Default(E_DB_SelfIfo.note, labelItem.getContent(), infoBean.getNote());
        W_UserInfo.Default(E_DB_SelfIfo.currentState, stateItem.getContent(), infoBean.getCurrentState());
        W_UserInfo.Default(E_DB_SelfIfo.xingzuo, selfXingZuo, infoBean.getXingZuo());
        W_UserInfo.Default(E_DB_SelfIfo.age, selfAge, infoBean.getAge());
        imageLoader.displayImage(infoBean.getHead(), mHeadImg, loadOptions, null);
        if (infoBean.getSex().equals(E_Http_Gender.MAN.toString())) {
            selfGenderImg.setImageResource(R.drawable.weibo_gender_m);
        } else if (infoBean.getSex().equals(E_Http_Gender.WOMEN.toString())) {
            selfGenderImg.setImageResource(R.drawable.weibo_gender_f);
        } else {
            selfGenderImg.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void requestMethod() {
        // TODO Auto-generated method stub

    }


    @Override
    protected void outFinish() {
        // TODO Auto-generated method stub

    }


}
